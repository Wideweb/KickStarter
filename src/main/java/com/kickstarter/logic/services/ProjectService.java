package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.*;
import com.kickstarter.models.DonationModel;
import com.kickstarter.models.ProjectModel;
import com.kickstarter.models.RewardModel;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService implements IProjectService {

    @Resource(name = "projectRepository")
    private IRepository<Project> projectRepository;

    @Resource(name = "projectTypeRepository")
    private IRepository<ProjectType> projectTypeRepository;

    @Resource(name = "countryRepository")
    private IRepository<Country> countryRepository;

    @Resource(name = "rewardRepository")
    private IRepository<Reward> rewardRepository;

    @Resource(name = "donationRepository")
    private IRepository<Donation> donationRepository;

    @Resource(name = "userService")
    private IUserService userService;

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
        Project project = projectRepository.getById(projectId);
        long diff = new Date().getTime() - project.getStartDate().getTime();
        int daysToGo = project.getFundingDuration() - (int)diff / (24 * 60 * 60 * 1000);
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(project.getId());
        projectModel.setName(project.getName());
        projectModel.setOwner(project.getOwner().getUsername());
        projectModel.setCountryId(project.getCountry().getId());
        projectModel.setProjectTypeId(project.getProjectType().getId());
        projectModel.setDescription(project.getDescription());
        projectModel.setDaysToGo(daysToGo);
        projectModel.setBackers(0);
        projectModel.setPledged(0);
        projectModel.setFundingGoal(project.getFundingGoal());
        projectModel.setFundingDuration(project.getFundingDuration());

        for(Integer i = 0; i < project.getRewards().size(); i++){
            Reward reward = project.getRewards().get(i);
            RewardModel rewardModel = new RewardModel();
            rewardModel.setId(reward.getId());
            rewardModel.setAmount(reward.getAmount());
            rewardModel.setDescription(reward.getDescription());
            rewardModel.setProjectId(project.getId());
            projectModel.getRewards().add(rewardModel);
        }

        return projectModel;
    }

    public List<Project> getUserProjects(String userName){
        return projectRepository
                .getAll()
                .stream()
                .filter((Project p) -> p.getOwner().getUsername().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

    public List<Project> getAll(){
        return projectRepository.getAll();
    }

    public void donateToProject(DonationModel model, String userName){
        Donation donation = new Donation();
        donation.setProject(projectRepository.getById(model.getProjectId()));
        donation.setUser(userService.getUserByName(userName));
        donation.setAmount(model.getAmount());
        if(model.getRewardId() >= 0){
            donation.setReward(rewardRepository.getById(model.getRewardId()));
        }
        donationRepository.add(donation);
    }
}
