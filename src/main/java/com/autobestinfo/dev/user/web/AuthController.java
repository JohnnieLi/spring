package com.autobestinfo.dev.user.web;

import com.autobestinfo.dev.core.CoreResponseBody;
import com.autobestinfo.dev.core.security.SecurityUtils;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.autobestinfo.dev.core.security.SecurityUtils.HEADER_STRING;
import static com.autobestinfo.dev.core.security.SecurityUtils.TOKEN_PREFIX;

@RestController
@RequestMapping("auth/")
public class AuthController {

    //Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZXIyIiwiZXhwIjoxNTI4OTg0NTc1fQ.fRgjcr803EPjgokkDvlParNu3nWvev37DB2zY1FT9ZznHUxOECRd7LiGtjIUFkQbNcklp9dgcqdmWP9pcl4XBw

    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //reference: http://www.baeldung.com/get-user-in-spring-security
    @PostMapping("login")
    public ResponseEntity<CoreResponseBody> login(Authentication auth) {
        try{
            String username = (String)auth.getPrincipal();
            User user = this.userService.findByUsername(username);
            CoreResponseBody responseBody = new CoreResponseBody<>(true, user, "has test user", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<>(false, null,"has test error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("googleLogin")
    public ResponseEntity<CoreResponseBody> googleLogin(@RequestBody User person, HttpServletResponse res) {
        try{
            User user = this.userService.findByGoogle(person.getUsername());
            if(user == null){
                user = this.userService.create(person);
            }
            String token = SecurityUtils.generateToken(user.getUsername());
            CoreResponseBody responseBody = new CoreResponseBody<>(true, user, "has test user", null);
            res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<>(false, null,"has test error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("facebookLogin")
    public ResponseEntity<CoreResponseBody> facebookLogin(@RequestBody User person, HttpServletResponse res) {
        try{
            User user = this.userService.findByFacebook(person.getUsername());
            if(user == null){
                user = this.userService.create(person);
            }
            String token = SecurityUtils.generateToken(user.getUsername());
            CoreResponseBody responseBody = new CoreResponseBody<>(true, user, "has test user", null);
            res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<>(false, null,"has test error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }





}
