package com.app.controller.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cassandra on 6/7/14.
 */
@Controller
public class GenericController {
    private static final Logger LOG = LoggerFactory.getLogger(GenericController.class);

    @RequestMapping("/login")
    public String helloWorld(Model model) {
        return "logMeIn";
    }
}
