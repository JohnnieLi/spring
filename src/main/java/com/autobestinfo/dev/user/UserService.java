package com.autobestinfo.dev.user;

import org.springframework.security.access.prepost.PreAuthorize;

interface UserService {

    void login(User user);


    //@PreAuthorize("hasRole('ADMIN')")
    void updateUser(User user);

}
