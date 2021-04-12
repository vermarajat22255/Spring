package com.authentication.service;

import com.authentication.entity.UserEntity;
import com.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails getUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
        return userDetails;
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
//        callNotificationService.createNotification(new Notification(userEntity.getUserId(), userEntity.getEmailId(),"Create", "Create user event", "Success"));
    }

    @Override
    public void deleteUser(String userName) {
        userRepository.deleteByUsername(userName);
    }
}
