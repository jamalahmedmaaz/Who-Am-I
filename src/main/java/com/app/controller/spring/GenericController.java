package com.app.controller.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cassandra on 6/7/14.
 */
@Controller
public class GenericController {
    @RequestMapping("/login")
    public String helloWorld(Model model) {
        return "logMeIn";
    }
}
