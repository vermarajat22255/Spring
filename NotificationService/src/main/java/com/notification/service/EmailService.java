package com.notification.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendEmail(String mailTo, String subject, String message);
}
