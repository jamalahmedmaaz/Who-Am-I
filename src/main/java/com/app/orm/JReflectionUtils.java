package com.app.orm;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Created by cassandra on 6/23/14.
 */
public class JReflectionUtils {

    public static Set<Class<?>> getAllMappedEntities(String packageLocation) {
        Reflections reflections = new Reflections(packageLocation);
        return reflections.getTypesAnnotatedWith(JCassandraEntity.class);
    }

    public static void main(String arp[]) throws ClassNotFoundException {
        Set<Class<?>> entities = getAllMappedEntities("com.app.orm");
        for (Class<?> entity : entities) {
            getTableName(entity);
            getColumnName(entity);
        }

    }

    public static String getTableName(Class<?> entity) {
        JCassandraEntity annotation = entity.getAnnotation(JCassandraEntity.class);
        System.out.println(annotation.name());
        return annotation.name();
    }

    public static Map<String, Pair> getColumnName(Class<?> entity) throws ClassNotFoundException {
        Map<String, Pair> mapOfFieldToDbColumnMapping = Maps.newHashMap();
        Class aClass = Class.forName(entity.getName());
        for (Field field : aClass.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                Class<? extends Annotation> fieldAnnotation = annotation.annotationType();
                if (fieldAnnotation == JCassandraColumn.class) {
                    JCassandraColumn jCassandraColumn = field.getAnnotation(JCassandraColumn.class);
                    String dbFieldName = null;
                    if (jCassandraColumn != null && jCassandraColumn.name() != null && StringUtils.isNotBlank(jCassandraColumn.name())) {
                        dbFieldName = jCassandraColumn.name();
                    } else {
                        dbFieldName = field.getName();
                    }
                    mapOfFieldToDbColumnMapping.put(field.getName(), new Pair<String, String>(dbFieldName, mapToDataSourceType(field.getType().getSimpleName())));
                }
                if (fieldAnnotation == JPrimaryKey.class) {
                    JPrimaryKey jPrimaryKey = field.getAnnotation(JPrimaryKey.class);
                    String dbFieldName = null;
                    if (jPrimaryKey != null && jPrimaryKey.name() != null && StringUtils.isNotBlank(jPrimaryKey.name())) {
                        dbFieldName = jPrimaryKey.name();
                    } else {
                        dbFieldName = field.getName();
                    }
                    mapOfFieldToDbColumnMapping.put(field.getName(), new Pair<String, String>(dbFieldName, mapToDataSourceType(field.getType().getSimpleName())));
                }
            }

        }
        return mapOfFieldToDbColumnMapping;
    }

    private static String mapToDataSourceType(String javaDataTypeString) {
        return javaDataTypeString;
    }
}
