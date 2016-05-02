package com.kickstarter.logic.services;


import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.ProjectType;
import com.kickstarter.models.ProjectTypeModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToBeanResultTransformer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ProjectTypeService implements IProjectTypeService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "projectTypeRepository")
    private IRepository<ProjectType> projectTypeRepository;

    @Override
    public List<ProjectTypeModel> getAll() {
        Session session = sessionFactory.openSession();
        return session.createSQLQuery("Select  projecttypes.id, projecttypes.name, " +
                "cast(count(projects.id) as Integer)" +
                "from projecttypes inner join projects \n" +
                "on projecttypes.id = projects.project_type_id group by projecttypes.id").
                setResultTransformer(new AliasToBeanResultTransformer(ProjectTypeModel.class))
                .list();
    }
}
