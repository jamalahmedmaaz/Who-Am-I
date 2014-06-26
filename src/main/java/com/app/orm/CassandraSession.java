package com.app.orm;

import com.datastax.driver.core.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by cassandra on 6/23/14.
 */
@Service
public class CassandraSession {

    private Map<String, DataMap> mappedEntities = Maps.newHashMap();
    private JUnitOfWork jUnitOfWork;

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
        jQuery.addCriteria(JCriteria.equals("userName", "johndoe"));
        List entities = jQuery.getResults();
        System.out.println(cassandraSession);
    }

    public JQuery createQuery(Class aClass) {
        return new JQuery(aClass, this);
    }

    public List getResult(JQuery jQuery) {
        if (jUnitOfWork != null && jUnitOfWork.getObject(jQuery.getIdentifier()) != null) {
            return new ArrayList((Collection) jUnitOfWork.getObject(jQuery.getIdentifier()));
        }
        return execute(jQuery);
    }

    private List execute(JQuery jQuery) {
        // Need to move this loaders.
        String query = jQuery.generateSelectQuery();
        Statement statement = new SimpleStatement(query);
        CassandraConnector cassandraConnector = new CassandraConnector();
        Session session = cassandraConnector.getSession();
        System.out.println(statement.toString());
        ResultSet resultSet = session.execute(statement);
        return resultToEntityMapper(resultSet, jQuery);
    }

    private List resultToEntityMapper(ResultSet resultSet, JQuery jQuery) {
        List list = Lists.newArrayList();
        for (Row row : resultSet) {
            try {
                list.add(createEntityFromRows(row, jQuery.getKlass()));
            } catch (IllegalAccessException e) {

            } catch (InstantiationException e) {

            }
        }
        return list;
    }

    private Object createEntityFromRows(Row row, Class klass) throws IllegalAccessException, InstantiationException {
        Object t = klass.newInstance();
        DataMap dataMap = mappedEntities.get(klass.getSimpleName());
        for (ColumnMap columnMap : dataMap.getColumnMaps()) {
            ReflectionUtils.makeAccessible(columnMap.getField());
            String typeName = columnMap.getField().getType().getSimpleName();
            //System.out.println(typeName);
            if (typeName.equalsIgnoreCase("String")) {
                ReflectionUtils.setField(columnMap.getField(), t, row.getString(columnMap.getColumnName()));
            }
            if (typeName.equalsIgnoreCase("long")) {
                ReflectionUtils.setField(columnMap.getField(), t, row.getLong(columnMap.getColumnName()));
            }
            if (typeName.equalsIgnoreCase("ByteBuffer")) {
                ReflectionUtils.setField(columnMap.getField(), t, row.getBytes(columnMap.getColumnName()));
            }
        }
        return t;
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

    public DataMap getDataMap(String entityName) {
        return mappedEntities.get(entityName);
    }
}

