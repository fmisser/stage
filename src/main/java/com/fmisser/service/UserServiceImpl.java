package com.fmisser.service;

import com.fmisser.model.Role;
import com.fmisser.model.User;
import com.fmisser.model.UserCreateForm;
import com.fmisser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by fmisser on 2016/6/25.
 *
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("username"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("USER");
        roles.add(role);
        user.setRoles(roles);
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        return userRepository.save(user);
    }
}
