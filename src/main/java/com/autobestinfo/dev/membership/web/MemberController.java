package com.autobestinfo.dev.membership.web;

import com.autobestinfo.dev.core.CoreResponseBody;
import com.autobestinfo.dev.core.emailSender.EmailServiceImpl;
import com.autobestinfo.dev.membership.Membership;
import com.autobestinfo.dev.membership.MembershipServiceImpl;
import com.autobestinfo.dev.user.User;
import com.autobestinfo.dev.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("members/")
public class MemberController {


    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;
    private final MembershipServiceImpl memberService;

    @Autowired
    public MemberController(UserServiceImpl userService, MembershipServiceImpl memberService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.memberService = memberService;
        this.emailService = emailService;
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USERS')")  //if using hasAuthority, then  need Prefix "ROLE_" setting
    public ResponseEntity<CoreResponseBody> createMember(Authentication auth, @RequestBody MemberForm body) {
        try{

            User applicationUser  = (User)auth.getPrincipal();
//            Membership membership = this.memberService.createMembership(applicationUser);
//            applicationUser.setMembership(membership);
//            User userResult = this.userService.update(applicationUser);
            String testResult = "token: " + body.token +" plan: "+ body.planId + " discount: "+ body.discount+ " sub: "+ body.subscriptionId;
            CoreResponseBody responseBody = new CoreResponseBody<>(true, testResult,"no error",null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("update")
    @PreAuthorize("hasRole('ROLE_USERS')")  //if using hasAuthority, then  need Prefix "ROLE_" setting
    public ResponseEntity<CoreResponseBody> update(Authentication auth) {
        try{
            User applicationUser  = (User)auth.getPrincipal();
            Membership membership = this.memberService.updateMembership(applicationUser);
            this.emailService.sendSimpleMessage("jiangqi2015@outlook.com","JavaEmailTest", "test text");
            CoreResponseBody responseBody = new CoreResponseBody<>(true, membership,"no error",null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("delete")
    @PreAuthorize("hasRole('ROLE_USERS')")  //if using hasAuthority, then  need Prefix "ROLE_" setting
    public ResponseEntity<CoreResponseBody> delete(Authentication auth) {
        try{
            User applicationUser  = (User)auth.getPrincipal();
            Boolean deleted = this.memberService.deleteMembership(applicationUser);
            CoreResponseBody responseBody = new CoreResponseBody<>(true, deleted,"member deleted",null);
            return new ResponseEntity<>(responseBody,HttpStatus.OK);
        }catch (Exception err){
            CoreResponseBody responseBody = new CoreResponseBody<List>(false, null,"has error", err);
            return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
    }


}

