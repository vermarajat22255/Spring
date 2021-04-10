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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Bean(name = "call")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CallNotificationService getCallNotificationService(){
        return new CallNotificationService();
    }
    // use @Resource(name = "call") when auto-wiring
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){ return new RestTemplate();}

    @Override
    @Order(1)
    public void run(String... args) throws Exception {
        User user = new User("1", "abc.gmail.com", "abc");
        userService.createUser(user);
    }
}
