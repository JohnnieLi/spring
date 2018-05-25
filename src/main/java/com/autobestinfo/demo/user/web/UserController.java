package com.autobestinfo.demo.user.web;

import com.autobestinfo.demo.core.CoreResponseBody;
import com.autobestinfo.demo.user.User;
import com.autobestinfo.demo.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CoreResponseBody> getAll() {
        try{
            List<User> users = this.userService.findAll();
            CoreResponseBody responseBody = new CoreResponseBody<List>(true, users, "has users", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


}
