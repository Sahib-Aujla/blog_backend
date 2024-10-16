package com.gblog.blog.controllers;

import com.gblog.blog.entity.User;
import com.gblog.blog.services.EmailService;
import com.gblog.blog.services.UserService;
import com.gblog.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtutil;

    @Autowired
    EmailService emailService;



    @Autowired
    AuthenticationManager  authenticationManager;
    @PostMapping("/signup")
    ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            if(user.getEmail()!=null) {
                emailService.sendEmail(user.getEmail(),"Welcome","Thank you for signing up");
            }
            return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody User user) {
        try{
            var v= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            String jwt=jwtutil.generateToken(v.getName());
           return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
