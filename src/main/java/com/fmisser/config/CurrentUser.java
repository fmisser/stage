package com.fmisser.config;

import com.fmisser.model.Role;
import com.fmisser.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fmisser on 2016/7/25.
 *
 */

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailService.class);

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public Long getId() {
        return this.user.getId();
    }

    public Set<Role> getRole() {
        return this.user.getRoles();
    }

    private static Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        return authorities;
    }
}
