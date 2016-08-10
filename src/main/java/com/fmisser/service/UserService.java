package com.fmisser.service;

import com.fmisser.model.User;
import com.fmisser.model.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by fmisser on 2016/6/25.
 *
 */

public interface UserService {

    Optional<User> getUserById(long id);
    Optional<User> getUserByUsername(String username);
    Collection<User> getAllUsers();
    User create(UserCreateForm form);
}
