package com.app.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by cassandra on 6/5/14.
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        SerializationConfig sConfig = OBJECT_MAPPER.getSerializationConfig();

        //Null values will not be serialized
        sConfig = sConfig.withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        OBJECT_MAPPER.setSerializationConfig(sConfig);
    }


    @Override
    public ObjectMapper getContext(Class<?> type) {
        return OBJECT_MAPPER;
    }
}