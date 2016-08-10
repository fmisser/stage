package com.fmisser.service;

import com.fmisser.config.CurrentUser;

/**
 * Created by fmisser on 2016/7/27.
 *
 */

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
