package com.app.repository;

import com.app.model.AbstractEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by cassandra on 6/9/14.
 */
public abstract class AbstractCassandraDaoImpl<T extends AbstractEntity, ID extends Serializable>
        implements AbstractCassandraDao<T, ID> {

    private final Class<T> daoType;

    public AbstractCassandraDaoImpl() {
        this.daoType = getEntityType();

    }

    protected abstract Logger getLogger();

    @SuppressWarnings("unchecked")
    public Class<T> getEntityType() {
        if (daoType == null) {
            return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        } else {
            return daoType;
        }
    }

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findByExternalId(String externalId) {
        return null;
    }

    @Override
    public List<T> findByExample(T exampleInstance) {
        return null;
    }

    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        return null;
    }

    @Override
    public List<T> findByProperties(Map<String, ? extends Serializable> keyValuePairs) {
        return null;
    }

    @Override
    public T insertOrUpdate(T instance) {
        return null;
    }

    @Override
    public void delete(T persistentInstance) {

    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public void clear() {

    }
}
