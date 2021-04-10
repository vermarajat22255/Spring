package com.authentication.controller;

import com.authentication.model.AuthRequest;
import com.authentication.service.UserService;
import com.authentication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@PropertySource("classpath:Configurations.properties")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("${AuthController.auth}")
    public String authUser(@RequestBody @Valid AuthRequest authRequest){

        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        String token = "";
        if(userService.getUser(username)!= null && password.equals(userService.getUser(username).getPassword())){
             token = jwtUtil.generateToken(new User(authRequest.getUsername(), authRequest.getPassword(), new ArrayList<>()));
        }
        return token;
    }
    @PostMapping("${AuthController.validate}")
    public boolean validateToken(@RequestHeader(name = "Authorization") String authToken){
        authToken = authToken.split("Bearer ")[1];
        System.out.println("token --> "+authToken);
        return jwtUtil.validateToken(authToken, userService.getUser(jwtUtil.getUserNameFromToken(authToken)));
    }
}
