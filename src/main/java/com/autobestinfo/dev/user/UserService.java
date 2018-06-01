package com.autobestinfo.dev.user;

import org.springframework.security.access.prepost.PreAuthorize;

interface UserService {

    void login(User user);

    User findByUsername(String username);

    User findByGoogle(String username);

    User findByFacebook(String username);

    void updateUser(User user);

}
