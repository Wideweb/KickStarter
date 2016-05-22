package com.kickstarter.entitiesRepositories;

public abstract class RepositoryBase<TEntity> implements IRepository<TEntity>
{
    protected Class typeParameterClass;

    public RepositoryBase(String typeParameter){
        typeParameterClass = getEntityClass(typeParameter);
    }

    private Class getEntityClass(String typeParameter) {
        try {
            return Class.forName(typeParameter);
        }
        catch( Exception e ) {
            return null;
        }
    }
}
