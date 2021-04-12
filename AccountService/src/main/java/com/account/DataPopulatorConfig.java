package com.account;

import com.account.model.AccountEntity;
import com.account.model.TransactionEntity;
import com.account.model.User;
import com.account.model.UserEntity;
import com.account.service.CallNotificationService;
import com.account.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

// config files got called first and commandline configurations are called later
@Configuration
public class DataPopulatorConfig implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("1", "abc.gmail.com", "abc");
//        userService.createUser(user);
    }
}
