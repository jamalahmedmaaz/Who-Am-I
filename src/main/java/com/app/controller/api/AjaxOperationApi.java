package com.app.controller.api;

import com.app.service.AjaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by cassandra on 6/9/14.
 */
@Path("ajax")
@Produces(MediaType.APPLICATION_JSON)
public class AjaxOperationApi {
    private static final Logger LOG = LoggerFactory.getLogger(LoginApi.class);

    @Autowired
    private AjaxService ajaxService;


}
