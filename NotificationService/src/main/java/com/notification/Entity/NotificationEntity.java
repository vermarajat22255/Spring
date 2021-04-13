package com.notification.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
//@NamedQuery(name = "findByUserEmail", query = "from Notification_Table n where n.emailId = :emailId")
@Entity(name = "Notification_Table")
public class NotificationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private String userId;
    private String emailId;
    private String eventType;
    private String message;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    private String status;
}
