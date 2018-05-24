package com.autobestinfo.demo.user.web;

import com.autobestinfo.demo.user.User;
import com.autobestinfo.demo.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("users/getAll")
    public List<User> getAll() {
        return this.userService.findAll();
    }


//    @RequestMapping("users/create")
//    public User creating() {
//        return this.userService.creating();
//    }

}
