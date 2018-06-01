package com.autobestinfo.dev.user.web;

import com.autobestinfo.dev.core.CoreResponseBody;
import com.autobestinfo.dev.core.security.SecurityUtils;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    //Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE1Mjc3NzMwMTQsImV4cCI6MTU1OTMwOTAzMywiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoidGVzdGVyIiwiX2lkIjoiNWIwZmYzOTIyMTdhZDQyN2UwZjgwOTY5IiwiUm9sZSI6IkFETUlOIn0.PGFnOncrTpbPl6sFtBHbDT6apUlJH_Ew9wIgVNQGIHoBXqysOQNg1Xg7xy7S9wnaCCmIzeyVbVqpo2fjIg32AA

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
