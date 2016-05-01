package com.kickstarter.entitiesRepositories.fakes;

import com.kickstarter.entitiesRepositories.RepositoryBase;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class FakeRepository<TEntity> extends RepositoryBase<TEntity>{

    private final FakeDbContext dbContext;

    public FakeRepository(){
        super("");
        dbContext = null;
    }

    public FakeRepository(String typeParameter){
        super(typeParameter);
        dbContext = new FakeDbContext();
    }

    @Override
    public TEntity getById(Integer id) {
        return (TEntity) dbContext.getEntities(typeParameterClass).get(id);
    }

    @Override
    public List getAll() {
        return dbContext.getEntities(typeParameterClass);
    }

    @Override
    public Integer add(TEntity entity) {
        return dbContext.addEntity(entity);
    }

    @Override
    public void update(TEntity entity) {

    }

    @Override
    public void delete(TEntity entity) {

    }
}
