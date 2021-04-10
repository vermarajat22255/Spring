package com.account.controller;

import com.account.model.Notification;
import com.account.model.NotificationResponse;
import com.account.model.User;
import com.account.service.CallNotificationService;
import com.account.service.UserService;
import com.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@PropertySource("classpath:Configurations.properties")
public class UserController {
    @Autowired
    UserService userService;

    @Resource(name = "call")
    CallNotificationService notificationService;

    @GetMapping("${UserController.GetNotificationsByUserId}")
    public NotificationResponse notifications(@PathVariable String userId){
        return notificationService.getNotifications(userId);
    }
    @GetMapping("${UserController.GetAllUsers}")
    public List<User> getAllUser(){
        return userService.getUsers();
    }
    @GetMapping("${UserController.GetUsersById}")
    public User getUser(@PathVariable (required = false) String id){
        return userService.getUser(id);
    }
    @PostMapping("${UserController.CreateUsers}")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

}
