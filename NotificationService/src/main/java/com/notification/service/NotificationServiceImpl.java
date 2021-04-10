package com.notification.service;

import com.notification.Entity.NotificationEntity;
import com.notification.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<NotificationEntity> getNotification(String userId) {
        logger.info("Get Notification Logger");
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public Long createNotification(NotificationEntity notificationEntity) {
        logger.info("Create Notification Logger");
        notificationRepository.save(notificationEntity);
        return notificationEntity.getNotificationId();
    }
}
