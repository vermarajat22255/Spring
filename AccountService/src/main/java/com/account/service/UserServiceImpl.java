package com.account.service;

import com.account.model.*;
import com.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;
    @Autowired
    CallNotificationService callNotificationService;
    @Autowired
    CallAuthService authService;

    @Override
    public List<User> getUsers() {
       List<UserEntity> entities = repository.findAll();
        List<User> users = new ArrayList();
       for(UserEntity e: entities){
           User u = new User(e.getUserId(), e.getEmailId(), e.getUserName());
           u.setBalance(e.getAccountEntity().getBalance());
           users.add(u);
       }
       return users;
    }

    @Override
    public User getUser(String id) {
        UserEntity userEntity = repository.findById(id).orElse(null);
        User u = new User(userEntity.getUserId(), userEntity.getEmailId(), userEntity.getUserName());
        u.setBalance(userEntity.getAccountEntity().getBalance());
        return u;
    }

    @Override
    public User createUser(User user) {
        // call auth create user with same username, password, emailId --> return boolean
        authService.createAuthUser(user);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(0f);
        ArrayList<TransactionEntity> transactions = new ArrayList<>();
        transactions.add(new TransactionEntity(UUID.randomUUID().toString(), 0f, new Date(), "Created","INR"));
        accountEntity.setTransactions(transactions);
        UserEntity userEntity = new UserEntity(UUID.randomUUID().toString(), user.getEmailId(), user.getUsername(), accountEntity);
        repository.save(userEntity);

        try {
            callNotificationService.createNotification(new Notification(userEntity.getUserId(), userEntity.getEmailId(), "Create", "Create user event", "Success"));
        }catch (IllegalStateException ex){
            authService.deleteAuthUser(user);
        }
        user.setUserId(userEntity.getUserId());
        // mask password/ null password
        user.setPassword(null);
        return user;
    }
}
