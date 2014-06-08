package com.app.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by cassandra on 6/9/14.
 */
public interface AbstractCassandraDao<T, ID extends Serializable> {

    public abstract T findById(ID id);

    public abstract List<T> findAll();

    public abstract T findByExternalId(final String externalId);

//    public abstract List<T> findByCriteria(final Criterion... criteria);

    public abstract List<T> findByExample(T exampleInstance);

    public abstract List<T> findByExample(T exampleInstance, String... excludeProperty);

    public abstract List<T> findByProperties(final Map<String, ? extends Serializable> keyValuePairs);

    public abstract T insertOrUpdate(T instance);

    public abstract void delete(T persistentInstance);

    public abstract void delete(ID id);

    public abstract void deleteAll();

    public void flush();

    public void clear();
}
