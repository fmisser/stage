package com.fmisser.model;

import com.fmisser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by fmisser on 2016/6/30.
 */

@Component
public class UserCreateValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateUsername(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "两次输入的密码不一致");
        }
    }

    private void validateUsername(Errors errors, UserCreateForm form) {
        if (userService.getUserByUsername(form.getUsername()).isPresent()) {
            errors.reject("username.exist", "该用户已存在");
        }
    }
}
