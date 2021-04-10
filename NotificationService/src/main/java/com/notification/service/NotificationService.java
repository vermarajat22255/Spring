package com.notification.service;

import com.notification.Entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationEntity> getNotification(String userId);
    Long createNotification(NotificationEntity notificationEntity);
}
