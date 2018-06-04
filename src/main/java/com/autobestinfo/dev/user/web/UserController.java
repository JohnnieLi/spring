package com.autobestinfo.dev.user.web;

import com.autobestinfo.dev.core.CoreResponseBody;
import com.autobestinfo.dev.membership.Membership;
import com.autobestinfo.dev.membership.MembershipServiceImpl;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users/")
public class UserController {


    private final UserServiceImpl userService;
    private final MembershipServiceImpl memberService;

    @Autowired
    public UserController(UserServiceImpl userService, MembershipServiceImpl memberService) {
        this.userService = userService;
        this.memberService = memberService;
    }


    // http://www.baeldung.com/registration-verify-user-by-email
    @GetMapping("add")
    public ResponseEntity<CoreResponseBody> add() {
        try{
            User userResult = this.userService.addTest();
            CoreResponseBody responseBody = new CoreResponseBody<>(true, userResult, "add 1 user", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<User>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("delete")
    public ResponseEntity<CoreResponseBody> delete(Authentication auth) {
        try{
            User applicationUser  = (User)auth.getPrincipal();
            this.userService.deleteById(applicationUser.get_Id());
            CoreResponseBody responseBody = new CoreResponseBody<>(true, null, "delete 1 user", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<User>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
//  @PreAuthorize("isAuthenticated()")
//  @PreAuthorize("hasAuthority('hasAuthorityANONYMOUS')")   //if using hasAuthority, then don't need Prefix "ROLE_" setting
//  @PreAuthorize("hasRole('ROLE_USERS')")  //if using hasAuthority, then  need Prefix "ROLE_" setting
    public ResponseEntity<CoreResponseBody> getAll(Authentication auth) {
        try{
            User applicationUser  = (User)auth.getPrincipal();
            List<User> users = this.userService.findAll();
            CoreResponseBody responseBody = new CoreResponseBody<List>(true, users, "has 1 users", null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }
}
