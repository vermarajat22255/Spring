package com.notification.batch;

import com.notification.Entity.NotificationEntity;
import com.notification.repository.NotificationRepository;
import com.notification.service.EmailService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationProcessor implements ItemProcessor<NotificationEntity, NotificationEntity> {
    @Autowired
    NotificationRepository repository;
    @Autowired
    EmailService emailService;

    @Override
    public NotificationEntity process(final NotificationEntity entity) throws Exception {
        emailService.sendEmail(entity.getEmailId(), entity.getEventType()+" has been created", entity.getMessage());
        entity.setStatus("Sent Success");
        return entity;
    }
}
