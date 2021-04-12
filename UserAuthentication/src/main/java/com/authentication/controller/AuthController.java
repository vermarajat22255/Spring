package com.authentication.controller;

import com.authentication.entity.UserEntity;
import com.authentication.model.AuthRequest;
import com.authentication.model.CreateUserRequest;
import com.authentication.repository.UserRepository;
import com.authentication.service.UserService;
import com.authentication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("${AuthController.createUser}")
    public ResponseEntity createUser(@RequestBody CreateUserRequest request){
        userService.createUser(new UserEntity(request.getEmailId(), request.getUsername(), request.getPassword()));
        return new ResponseEntity(HttpStatus.CREATED);
    }
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
    @DeleteMapping("/auth/delete")
    public ResponseEntity deleteUser(@RequestBody AuthRequest request){
        userService.deleteUser(request.getUsername());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
