package com.app.repository;

import com.app.model.AbstractEntity;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.springframework.cassandra.core.SessionCallback;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by cassandra on 6/9/14.
 */
public abstract class AbstractCassandraDaoImpl<T extends AbstractEntity, ID extends Serializable>
        implements AbstractCassandraDao<T, ID> {

    private final Class<T> daoType;

    @Resource(name = "cassandraTemplate")
    private CassandraTemplate cassandraTemplate;

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
        try {
            Map<String, Serializable> properties = Maps.newHashMapWithExpectedSize(1);
            properties.put("id", id);
            List<T> entities = findByProperties(properties);
        } catch (Exception e) {

        }
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
    public List<T> findByProperties(final Map<String, ? extends Serializable> keyValuePairs) {
        try {
            SessionCallback<T> sessionCallback = new SessionCallback() {
                @Override
                public Object doInSession(Session session) throws DataAccessException {
                    StringBuffer query = new StringBuffer("SELECT * FROM " + getEntityType())
                            .append(" ").append("WHERE ");
                    int counter = 0;
                    for (Map.Entry<String, ? extends Serializable> entry : keyValuePairs.entrySet()) {
                        if (counter > 0) {
                            query.append(" AND ");
                        }
                        query.append(entry.getKey()).append(" = ").append(entry.getValue());
                        counter++;
                    }
                    RegularStatement statement = (RegularStatement) new SimpleStatement(query.toString()).setConsistencyLevel(ConsistencyLevel.ALL);
                    return session.execute(statement);
                }
            };
            return (List<T>) cassandraTemplate.execute(sessionCallback);
        } catch (DataAccessException e) {
            getLogger().error("findByExample failed: " + e);
            throw e;
        }
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
