package com.fmisser.controller;

import com.fmisser.model.UserCreateForm;
import com.fmisser.model.UserCreateValidator;
import com.fmisser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * Created by fmisser on 2016/6/30.
 *
 */

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateValidator userCreateValidator;

    @Autowired
    public UserController(UserService userService, UserCreateValidator userCreateValidator) {
        this.userService = userService;
        this.userCreateValidator = userCreateValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public String getUserPage(@PathVariable Long id, Model model) {
        LOGGER.debug("getting user page for user={}", id);
        model.addAttribute("user", userService.getUserById(id)
                .orElseThrow((Supplier<RuntimeException>) () -> new NoSuchElementException(String.format("User=%s not found", id))));
        return "user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String getUserCreatePage(Model model) {
        model.addAttribute("form", new UserCreateForm());
        return "user_create";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={], bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user", e);
            return "user_create";
        }

        return "redirect:/users";
    }
}
