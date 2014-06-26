package com.app.orm;

import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by cassandra on 6/23/14.
 */
@Service
public class CassandraSession {

    private Map<String, DataMap> mappedEntities = Maps.newHashMap();
    private JUnitOfWork JUnitOfWork;


    public CassandraSession() throws ClassNotFoundException {
        initialized();
    }

    private void initialized() throws ClassNotFoundException {
        Set<Class<?>> entities = JReflectionUtils.getAllMappedEntities("com.app.orm");
        for (Class<?> entity : entities) {
            DataMap dataMap = new DataMap(entity, JReflectionUtils.getTableName(entity));
            Map<String, Triplet> columnToDBMap = JReflectionUtils.getColumnName(entity);
            for (Map.Entry<String, Triplet> entry : columnToDBMap.entrySet()) {
                dataMap.addColumn(entry.getKey(),
                        entry.getValue().getSecond().toString(),
                        entry.getValue().getFirst().toString(),
                        dataMap,
                        Boolean.valueOf(entry.getValue().getThird().toString()));
            }
            if (mappedEntities.get(entity.getSimpleName()) != null) {
                throw new RuntimeException("Duplicate entity Name");
            }
            mappedEntities.put(entity.getSimpleName(), dataMap);
        }
    }

    public static void main(String... ar) throws ClassNotFoundException {
        CassandraSession cassandraSession = new CassandraSession();
        JQuery jQuery = cassandraSession.createQuery(User.class);
        jQuery.addCriteria(JCriteria.equals("name", "don"));
        List entities = jQuery.getResults();
        System.out.println(cassandraSession);
    }

    public JQuery createQuery(Class aClass) {
        return new JQuery(aClass, this);
    }

    public List getResult(JQuery jQuery) {
        if (JUnitOfWork.getObject(jQuery.getIdentifier()) != null) {
            return new ArrayList((Collection) JUnitOfWork.getObject(jQuery.getIdentifier()));
        }

        // Need to move this loaders.
        String query = jQuery.generateSelectQuery();
        Statement statement = new SimpleStatement(query);
        return null;
    }

    public DataMap getDataMap(String entityName) {
        return mappedEntities.get(entityName);
    }
}
