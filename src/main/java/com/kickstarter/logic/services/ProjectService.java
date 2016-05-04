package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.*;
import com.kickstarter.models.DonationModel;
import com.kickstarter.models.ProjectModel;
import com.kickstarter.models.RewardModel;
import com.kickstarter.models.ProjectTypeModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService implements IProjectService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "projectRepository")
    private IRepository<Project> projectRepository;

    @Resource(name = "projectTypeRepository")
    private IRepository<ProjectType> projectTypeRepository;

    @Resource(name = "countryRepository")
    private IRepository<Country> countryRepository;

    @Resource(name = "donationRepository")
    private IRepository<Donation> donationRepository;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "rewardRepository")
    private IRepository<Reward> rewardRepository;

    @Resource(name = "rewardService")
    private IRewardService rewardService;

    @Resource(name = "donationService")
    private IDonationService donationService;

    public Integer save(ProjectModel projectModel, String userName){
        Project project = new Project();
        project.setId(projectModel.getId());
        project.setName(projectModel.getName());
        project.setOwner(userService.getUserByName(userName));
        project.setCountry(countryRepository.getById(projectModel.getCountryId()));
        project.setProjectType(projectTypeRepository.getById(projectModel.getProjectTypeId()));
        project.setDescription(projectModel.getDescription());
        project.setFundingDuration(projectModel.getFundingDuration());
        project.setFundingGoal(projectModel.getFundingGoal());
        project.setStartDate(new Date());

        for(Integer i = 0; i < projectModel.getRewards().size(); i++){
            RewardModel rewardModel = projectModel.getRewards().get(i);
            Reward reward = new Reward();
            reward.setId(rewardModel.getId());
            reward.setAmount(rewardModel.getAmount());
            reward.setDescription(rewardModel.getDescription());
            reward.setProject(project);
            project.getRewards().add(reward);
        }

        if(projectModel.getId() != null && projectModel.getId() > 0){
            projectRepository.update(project);
        }else{
            return projectRepository.add(project);
        }

        return project.getId();
    }

    public ProjectModel get(Integer projectId){
        return mapProjectToModel(projectRepository.getById(projectId));
    }

    public void approveProject(Integer projectId) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            Project project = projectRepository.getById(projectId);
            project.setApproved(true);
            long ltime = new Date().getTime() + project.getFundingDuration() * 24 * 60 * 60 * 1000;
            Date endDate = new Date(ltime);
            project.setEndDate(endDate);
            project.setStartDate(new Date());
            session.update(project);
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public void rejectProject(Integer projectId) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            Project project = projectRepository.getById(projectId);
            project.setApproved(false);
            session.update(project);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public List<ProjectModel> getUserProjects(String userName){
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getOwner().getUsername().equalsIgnoreCase(userName))
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    public List<ProjectModel> getAll(){
        return projectRepository
                .getAll()
                .stream()
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    public List<ProjectModel> getActive(){
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getApproved() != null
                        && p.getApproved() == true
                        && p.getEndDate().getTime() > new Date().getTime())
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    public List<ProjectModel> getProjectsForApproving(){
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getApproved() == null)
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectModel> getFinished() {
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getApproved() != null
                        && p.getApproved() == true
                        && p.getEndDate().getTime() < new Date().getTime())
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    public void donateToProject(DonationModel model, String userName){
        Donation donation = new Donation();
        donation.setProject(projectRepository.getById(model.getProjectId()));
        donation.setUser(userService.getUserByName(userName));
        donation.setAmount(model.getAmount());
        if(model.getRewardId() != null && model.getRewardId() >= 0){
            donation.setReward(rewardRepository.getById(model.getRewardId()));
        }
        donationRepository.add(donation);
    }
    
    public List<ProjectModel> getAllByCategory(Integer categoryId) {
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getApproved() != null
                        && p.getApproved() == true
                        && p.getEndDate().getTime() >= new Date().getTime()
                        && p.getProjectType().getId() == categoryId)
                .map(this::mapProjectToModel)
                .collect(Collectors.toList());
    }

    private ProjectModel mapProjectToModel(Project project){
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(project.getId());
        projectModel.setDonations(donationService.getDonationsByProjectId(project.getId()));
        projectModel.setRewards(rewardService.getRewardsByProjectId(project.getId()));
        projectModel.setDescription(project.getDescription());
        projectModel.setApproved(project.getApproved());
        projectModel.setBackers(projectModel.getDonations().size());
        projectModel.setCountryId(project.getCountry().getId());
        projectModel.setDaysToGo(GetDaysToGo(project));
        projectModel.setFundingDuration(project.getFundingDuration());
        projectModel.setFundingGoal(project.getFundingGoal());
        projectModel.setName(project.getName());
        projectModel.setOwner(project.getOwner().getUsername());
        projectModel.setPledged(CountPledged(projectModel.getDonations()));
        projectModel.setProjectTypeId(project.getProjectType().getId());

        return projectModel;
    }

    private int GetDaysToGo(Project project){
        long diff = new Date().getTime() - project.getStartDate().getTime();
        return project.getFundingDuration() - (int)diff / (24 * 60 * 60 * 1000);
    }

    private int CountPledged(List<DonationModel> donations){
        Integer pledged = 0;
        for(int i = 0; i < donations.size(); i++){
            pledged += donations.get(i).getAmount();
        }

        return pledged;
    }
}
