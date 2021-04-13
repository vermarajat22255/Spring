//package com.notification;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//    @Value("${NotificationService.mail.host}")
//    String host;
//    @Value("${NotificationService.mail.port}")
//    String port;
//
//    @Bean
//    public JavaMailSender getMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(Integer.parseInt(port));
//
//        mailSender.setUsername("veronica0@ethereal.email");
//        mailSender.setPassword("GFj4QqJAfpMyjnp1zH");
//
////        Properties props = mailSender.getJavaMailProperties();
////        props.put("mail.transport.protocol", "smtp");
////        props.put("mail.smtp.auth", "false");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.debug", "true");
//
//
////        Host	smtp.ethereal.email
////        Port	587
////        Security	STARTTLS
////        Username	veronica0@ethereal.email
////        Password	GFj4QqJAfpMyjnp1zH
//        return mailSender;
//    }
//}
