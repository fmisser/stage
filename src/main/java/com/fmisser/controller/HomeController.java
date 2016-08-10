package com.fmisser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fmisser on 2016/6/22.
 *
 */

@Controller
//@RestController
public class HomeController {

    @RequestMapping("/")
    public String getHomePage() {
        return "home";
    }
}
