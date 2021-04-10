package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String userId;
    private String emailId;
    private String userName;
    private float balance;

    public User(String userId, String emailId, String userName) {
        this.userId = userId;
        this.emailId = emailId;
        this.userName = userName;
    }
}
