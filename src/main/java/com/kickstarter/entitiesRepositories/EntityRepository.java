package com.kickstarter.entitiesRepositories;

import javax.annotation.Resource;
import java.util.List;

public class EntityRepository<TEntity> extends RepositoryBase<TEntity>{

    @Resource(name = "dbQuery")
    private DbQuery dbQuery;

    public EntityRepository(){
        super("");
    }

    public EntityRepository(String typeParameter){
        super(typeParameter);
    }

    @SuppressWarnings("unchecked")
    public TEntity getById(Integer id) {
        return (TEntity)dbQuery.tryGetResult(
                session -> session.get(typeParameterClass, id));
    }

    @SuppressWarnings("unchecked")
    public List<TEntity> getAll() {
        return (List<TEntity>)dbQuery.tryGetResult(session -> session
                .createCriteria(typeParameterClass)
                .list());
    }

    public Integer add(TEntity entity) {
        return (Integer) dbQuery.tryGetResult(session -> session.save(entity));
    }

    public void update(TEntity entity) {
        dbQuery.tryExecute(session -> session.update(entity));
    }

    public void delete(TEntity entity) {
        dbQuery.tryExecute(session -> session.delete(entity));
    }
}
