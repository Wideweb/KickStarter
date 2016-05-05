package com.kickstarter.logic.services;

import com.kickstarter.models.DonationModel;
import com.kickstarter.models.ProjectModel;

import java.util.List;

public interface IProjectService {
    Integer save(ProjectModel projectModel, String userName);
    ProjectModel get(Integer projectId);
    List<ProjectModel> getUserProjects(String userName);
    List<ProjectModel> getAll();
    void donateToProject(DonationModel model, String userName);
    List<ProjectModel> getAllByCategory(Integer categoryId);
    List<ProjectModel> getActive();
    List<ProjectModel> getProjectsForApproving();
    List<ProjectModel> getFinished();
    List<ProjectModel> find(String projectString);

    void approveProject(Integer projectId);
    void rejectProject(Integer projectId);
}
