package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.*;
import com.kickstarter.models.ProjectModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService implements IProjectService {

    @Resource(name = "projectRepository")
    private IRepository<Project> projectRepository;

    @Resource(name = "projectTypeRepository")
    private IRepository<ProjectType> projectTypeRepository;

    @Resource(name = "countryRepository")
    private IRepository<Country> countryRepository;

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
        project.setStartDate(projectModel.getStartDate());

        if(projectModel.getId() != null && projectModel.getId() > 0){
            projectRepository.update(project);
        }else{
            return projectRepository.add(project);
        }

        return project.getId();
    }

    public ProjectModel get(Integer projectId){
        Project project = projectRepository.getById(projectId);
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(project.getId());
        projectModel.setName(project.getName());
        projectModel.setCountryId(project.getCountry().getId());
        projectModel.setProjectTypeId(project.getProjectType().getId());
        projectModel.setDescription(project.getDescription());
        projectModel.setStartDate(project.getStartDate());
        projectModel.setFundingGoal(project.getFundingGoal());
        projectModel.setFundingDuration(project.getFundingDuration());

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
}
