package com.app.controller.api;

import com.app.model.UserInfo;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by cassandra on 6/5/14.
 */
@Path("whoami")
@Produces(MediaType.APPLICATION_JSON)
public class LoginApi {

    private static final Logger LOG = LoggerFactory.getLogger(LoginApi.class);

    @Autowired
    private UserService userService;

    @POST
    @Path("/login")
    public void login(UserInfo userInfo) {
        LOG.debug("login called with " + userInfo);
        userService.login(userInfo);
    }
}
