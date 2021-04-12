package com.authentication;

import com.authentication.entity.UserEntity;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig implements CommandLineRunner {
    @Autowired
    UserService userService;

    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity(1l, "abc@gmail.com", "abc", "123@xyz");
//        userService.createUser(user);
        user = new UserEntity(2l, "xyz@gmail.com", "xyz", "123@xyz");
//        userService.createUser(user);

        user = new UserEntity(3l, "jkh@gmail.com", "jhk", "jkh@xyz");
//        userService.createUser(user);

        user = new UserEntity(4l, "thx@gmail.com", "thx", "thx@xyz");
//        userService.createUser(user);

//        System.out.println(userService.getUser("xyz").getPassword());

    }
}
