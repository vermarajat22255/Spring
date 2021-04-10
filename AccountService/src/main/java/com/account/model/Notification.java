package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notification {
    private String userId;
    private String emailId;
    private String eventType;
    private String message;
    private String status;
}
