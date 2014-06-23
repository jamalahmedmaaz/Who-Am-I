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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public Object login(UserInfo userInfo) throws IOException {
        java.nio.file.Path imagePath = Paths.get("/home/cassandra/Desktop", "j.jpeg");
        byte[] imageBytes = Files.readAllBytes(imagePath);
        ByteBuffer byteBuffer = ByteBuffer.wrap(imageBytes);
        userInfo.setImage(byteBuffer);
        LOG.debug("login called with " + userInfo);
        return userService.findUser(userInfo);
    }
}
