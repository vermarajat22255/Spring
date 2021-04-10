package com.account.service;

import com.account.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    User getUser(String id);
    User createUser(User user);
}
