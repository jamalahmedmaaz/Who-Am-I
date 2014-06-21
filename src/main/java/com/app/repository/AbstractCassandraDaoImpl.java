package com.app.repository;

import com.app.model.AbstractEntity;
import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.Ordering;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.cassandra.core.SessionCallback;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

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

    protected abstract org.apache.log4j.Logger getLogger();

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
    public T findOne(Collection<Clause> criteria) {
        List<T> entities = findByCriteria(null, 1, criteria.toArray(new Clause[]{}));
        if (entities != null && entities.size() > 0) {
            return entities.get(0);
        } else {
            return null;
        }
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
    public List<T> findByCriteria(final Collection<Clause> criteria) {
        Ordering[] orders = {};
        return findByCriteria(orders, criteria);
    }

    public List<T> findByCriteria(final Ordering[] orders, final Collection<Clause> criteria) {
        return findByCriteria(orders, criteria.toArray(new Clause[]{}));
    }

    public List<T> findByCriteria(final Ordering[] orders, final Clause... criteria) {
        return findByCriteria(orders, null, criteria);
    }

    public List<T> findByCriteria(final Ordering[] orders, final Integer maxResults,
                                  final Clause... criteria) {
        return findByCriteria(orders, maxResults, null, criteria);
    }

    public List<T> findByCriteria(final Ordering[] orders, final Integer maxResults,
                                  Object cacheMode, final Clause... criteria) {
        return findByCriteria(orders, null, maxResults, cacheMode, new ArrayList<AliasContainer>(),
                false, criteria);
    }

    public List<T> findByCriteria(final Ordering[] orders, final Integer firstResult,
                                  final Integer maxResults, Object cacheMode,
                                  final List<AliasContainer> aliases,
                                  boolean dontGetFromJQueryCache, final Clause... criteria) {
        Select selectQuery = QueryBuilder.select().from(getEntityType().getSimpleName());

        for (Clause criterion : criteria) {
            selectQuery.where().and(criterion);
        }
        if (orders != null) {
            for (Ordering order : orders) {
                selectQuery.orderBy(order);
            }
        }
        /*if (maxResults != null) {
            selectQuery.limit(maxResults);
        }*/
        List<T> list = Lists.newArrayList();
        try {
            list = cassandraTemplate.query(selectQuery, new RowMapper<T>() {
                @Override
                public T mapRow(Row row, int rowNum) throws DriverException {
                    try {
                        return createEntityFromRows(row);
                    } catch (InstantiationException e) {
                        throw new RuntimeException();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException();
                    }
                }
            });
            return list;
        } catch (DataAccessException e) {
            getLogger().error("findByCriteria failed: " + e);
            throw e;
        } finally {
            //do halla gulla
        }
    }

    private T createEntityFromRows(Row row) throws IllegalAccessException, InstantiationException {
        Class c = getEntityType();
        T t = (T) c.newInstance();
        List<Field> fields = getAllFields(new LinkedList<Field>(), c);
        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            String typeName = field.getType().getSimpleName();
            //System.out.println(typeName);
            if (typeName.equals("String")) {
                ReflectionUtils.setField(field, t, row.getString(field.getName()));
            }
            if (typeName.equals("long")) {
                ReflectionUtils.setField(field, t, row.getLong(field.getName()));
            }
        }
        return t;
    }


    @Override
    public List<T> findByProperties(final Map<String, ? extends Serializable> keyValuePairs) {
        try {
            SessionCallback<T> sessionCallback = new SessionCallback() {
                @Override
                public Object doInSession(Session session) throws DataAccessException {
                    StringBuffer query = new StringBuffer("SELECT * FROM " + getEntityType().getSimpleName())
                            .append(" ").append("WHERE ");
                    int counter = 0;
                    for (Map.Entry<String, ? extends Serializable> entry : keyValuePairs.entrySet()) {
                        if (counter > 0) {
                            query.append(" AND ");
                        }
                        query.append(entry.getKey()).append(" = ").append("'" + entry.getValue() + "'");
                        counter++;
                    }
                    RegularStatement statement = (RegularStatement) new SimpleStatement(query.toString()).setConsistencyLevel(ConsistencyLevel.ALL);

                    return session.execute(statement).all();
                }
            };
            return (List<T>) cassandraTemplate.execute(sessionCallback);
        } catch (DataAccessException e) {
            getLogger().error("findByProperties failed: " + e);
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

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

}

