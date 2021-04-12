package com.authentication.service;

import com.authentication.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDetails getUser(String userName);
    public void createUser(UserEntity userEntity);
    public void deleteUser(String userName);
}
