package com.fmisser.service;

import com.fmisser.config.CurrentUser;
import com.fmisser.model.Role;
import org.springframework.stereotype.Service;

/**
 * Created by fmisser on 2016/7/27.
 *
 */

@Service
public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
//        return currentUser != null && (currentUser.getRole().contains())
        if (currentUser != null) {
            if (currentUser.getId().equals(userId)) {
                return true;
            } else {
                for (Role role : currentUser.getRole())
                    if (role.getRole().equals("ADMIN")) {
                        return true;
                    }
            }
        }
        return false;
    }
}
