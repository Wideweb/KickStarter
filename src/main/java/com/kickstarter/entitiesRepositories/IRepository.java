package com.kickstarter.entitiesRepositories;

import java.util.List;

public interface IRepository<TEntity> {

    TEntity getById(Integer id);
    List<TEntity> getAll();
    Integer add(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
}

