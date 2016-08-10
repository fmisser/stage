package com.fmisser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fmisser on 2016/7/25.
 *
 */

@Controller
@RequestMapping("/logout")
public class LogoutController {

    public String index() {
        return "logout";
    }

}
