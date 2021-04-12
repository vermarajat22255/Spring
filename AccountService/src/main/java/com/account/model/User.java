package com.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private String userId;
    private String emailId;
    private String username;
    private String password;
    private float balance;

    public User(String userId, String emailId, String username) {
        this.userId = userId;
        this.emailId = emailId;
        this.username = username;
    }
}
