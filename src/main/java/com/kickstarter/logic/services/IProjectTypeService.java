package com.kickstarter.logic.services;


import com.kickstarter.models.ProjectTypeModel;

import java.util.List;

public interface IProjectTypeService {
    List<ProjectTypeModel> getAll();
}
