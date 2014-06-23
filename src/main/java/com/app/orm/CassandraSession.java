package com.app.orm;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * Created by cassandra on 6/23/14.
 */
public class CassandraSession {

    private Map<String, DataMap> mappedEntities = Maps.newHashMap();

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
        System.out.println(cassandraSession);
    }

    public JQuery createQuery(Class aClass) {
        return new JQuery(aClass);
    }
}
