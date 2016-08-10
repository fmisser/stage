package com.fmisser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by fmisser on 2016/6/23.
 *
 */

@Controller
//@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(@RequestParam Optional<String> error, Model model) {
        if (error.isPresent()) {
            model.addAttribute("error", error.get());
        }
        return "login";
    }
}
