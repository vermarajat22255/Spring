package com.notification.controller;
import com.notification.Entity.NotificationEntity;
import com.notification.model.NotificationResponse;
import com.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:configurations.properties")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("${NotificationController.GetByUserId}")
    public NotificationResponse notifications(@PathVariable String userId){
        return new NotificationResponse(notificationService.getNotification(userId));
    }
    @PostMapping("${NotificationController.CreateNotification}")
    public Long createNotification(@RequestBody NotificationEntity notificationEntity){
        return notificationService.createNotification(notificationEntity);
    }
}
