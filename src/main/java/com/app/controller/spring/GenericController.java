package com.app.controller.spring;

import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cassandra on 6/7/14.
 */
@Controller
public class GenericController {
    private static final Logger LOG = LoggerFactory.getLogger(GenericController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Model model) {
        LOG.debug("login request ");
        return "logMeIn";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        LOG.debug("login request ");
        return "userDetail";
    }

}
