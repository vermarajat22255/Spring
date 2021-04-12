package com.account.service;

import com.account.model.Notification;
import com.account.model.NotificationResponse;
import com.account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@PropertySource("classpath:Configurations.properties")
public class CallAuthService {
    @Autowired
    RestTemplate restClient;
    @Value("${AuthController.createUser}")
    String createUrl;
    @Value("${AuthController.deleteAuthUser}")
    String deleteUrl;


    public ResponseEntity createAuthUser(User user){
        return restClient.postForObject(createUrl, user, ResponseEntity.class);
    }
    public void deleteAuthUser(User user){
        restClient.delete(deleteUrl,  user);
    }
}
