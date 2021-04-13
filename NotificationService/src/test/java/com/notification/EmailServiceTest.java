package com.notification;

import com.notification.service.EmailService;
import com.notification.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test
    public void sendEmail(){
        emailService.sendEmail("vermarajat22255@gmail.com","Hi, this is the first mail", "Trying to send a mail");
    }
}
