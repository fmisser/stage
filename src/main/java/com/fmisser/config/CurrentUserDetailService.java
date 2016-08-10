package com.fmisser.config;

import com.fmisser.model.User;
import com.fmisser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * Created by fmisser on 2016/6/22.
 *
 */

@Service
//@Transactional
public class CurrentUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailService.class);
    private UserService userService;

    @Autowired
    public CurrentUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with username={}", username);
        User user = userService.getUserByUsername(username)
                .orElseThrow((Supplier<RuntimeException>) () -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        return new CurrentUser(user);
    }
}
