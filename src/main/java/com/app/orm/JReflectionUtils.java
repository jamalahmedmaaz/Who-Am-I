package com.app.orm;

import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by cassandra on 6/23/14.
 */
public class JReflectionUtils {

    public static Set<Class<?>> getAllMappedEntities(String packageLocation) {
        Reflections reflections = new Reflections(packageLocation);
        return reflections.getTypesAnnotatedWith(JCassandraEntity.class);
    }

    public static void main(String arp[]) {
        Set<Class<?>> entities = getAllMappedEntities("com.app.orm");
        
    }
}
