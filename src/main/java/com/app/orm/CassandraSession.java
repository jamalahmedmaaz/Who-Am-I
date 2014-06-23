package com.app.orm;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by cassandra on 6/23/14.
 */
public class CassandraSession {
    private Map<String, DataMap> mappedEntities = Maps.newHashMap();

    public CassandraSession() {
        initialized();
    }

    private void initialized() {

    }


}
