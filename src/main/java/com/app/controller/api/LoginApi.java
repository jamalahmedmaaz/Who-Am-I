package com.app.controller.api;

import com.app.model.UserInfo;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by cassandra on 6/5
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class LoginApi {

    private static final Logger LOG = LoggerFactory.getLogger(LoginApi.class);

    @Autowired
    private UserService userService;

    @POST
    @Path("/findUser")
    public Object login(UserInfo userInfo) {
        LOG.debug("login called with " + userInfo);
        return userService.findUser(userInfo);
    }
}
