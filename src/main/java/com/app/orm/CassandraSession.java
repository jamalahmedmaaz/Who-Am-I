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
            Map<String, Pair> columnToDBMap = JReflectionUtils.getColumnName(entity);
            for (Map.Entry<String, Pair> entry : columnToDBMap.entrySet()) {
                dataMap.addColumn(entry.getKey(), entry.getValue().getSecond().toString(), entry.getValue().getFirst().toString(), dataMap);
            }
            mappedEntities.put(entity.getSimpleName(), dataMap);
            System.out.println(mappedEntities);
        }
    }

    public static void main(String... ar) throws ClassNotFoundException {
        CassandraSession cassandraSession = new CassandraSession();
    }
}
