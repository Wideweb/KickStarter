package com.kickstarter.logic.services;

import com.kickstarter.logic.domain.Project;
import com.kickstarter.models.DonationModel;
import com.kickstarter.models.ProjectModel;

import java.util.List;

public interface IProjectService {
    Integer save(ProjectModel projectModel, String userName);
    ProjectModel get(Integer projectId);
    List<Project> getUserProjects(String userName);
    List<Project> getAll();
    void donateToProject(DonationModel model, String userName);
    List<Project> getAllByCategory(Integer categoryId);
    List<Project> getActive();
    List<Project> getProjectsForApproving();
    void approveProject(Integer projectId);
    void rejectProject(Integer projectId);
}
