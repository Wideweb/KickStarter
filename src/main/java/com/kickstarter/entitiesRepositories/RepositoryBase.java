package com.kickstarter.entitiesRepositories;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Entity;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class RepositoryBase<TEntity> {

    final Class<TEntity> typeParameterClass;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public RepositoryBase(Class<TEntity> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public TEntity getById(Integer id) {
        return (TEntity) sessionFactory
                .openSession()
                .get(typeParameterClass, id);
    }

    public List getAll() {
        return sessionFactory
                .openSession()
                .createCriteria(typeParameterClass)
                .list();
    }

    public TEntity getFirst(String propertyName, Object propertyValue) {
        return  (TEntity) sessionFactory
                .openSession()
                .createCriteria(typeParameterClass)
                .setFirstResult(0)
                .setMaxResults(1)
                .add(Restrictions.eq(propertyName, propertyValue));
    }

    public void add(TEntity entity) {
        sessionFactory
                .openSession()
                .save(entity);
    }

    public void update(TEntity entity) {
        sessionFactory
                .openSession()
                .update(entity);
    }

    public void delete(TEntity entity) {
        sessionFactory
                .openSession()
                .delete(entity);
    }
}
