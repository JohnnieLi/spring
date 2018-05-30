package com.autobestinfo.dev.user.web;

import com.autobestinfo.dev.core.CoreResponseBody;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("auth/")
public class AuthController {


    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //reference: http://www.baeldung.com/get-user-in-spring-security
    @PostMapping("login")
    public ResponseEntity<CoreResponseBody> login(Authentication auth, @RequestBody User user) {
        try{

            String role =((User)auth.getPrincipal()).getRole();

            List<User> users = this.userService.findAll();
            CoreResponseBody responseBody = new CoreResponseBody<List>(true, users, "has test users", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has test error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }




}
