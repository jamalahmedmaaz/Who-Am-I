package com.app.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cassandra on 6/5/14.
 */
public class JerseyConfig extends ResourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyConfig.class);

    /**
     * Register JAX-RS application components.
     */
    public JerseyConfig() {
        //Controllers
        packages("com.app");

        //multi-part
        register(MultiPartFeature.class);

        //Response Filters
        register(JacksonFeature.class);

        //Register Object Mapper Provider
        register(ObjectMapperProvider.class);
    }
}
