package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(String mailTo, String subject, String message) {
//        SimpleMailMessage mail = new SimpleMailMessage();

        MimeMessage mimeMail = mailSender.createMimeMessage();
        MimeMessageHelper mail = null;
        try {
            mail = new MimeMessageHelper(mimeMail, true);
            mail.setFrom("no-reply@Account-service.com");
            mail.setSentDate(new Date());
            mail.setSubject(subject);
            mail.setText(message);
            mail.setTo(mailTo);

            // Add attachment
//            FileSystemResource file
//                    = new FileSystemResource(new File("src/main/resources/static/wtc.jpg"));
//            mail.addAttachment("Wtc", file);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMail);
    }
}
