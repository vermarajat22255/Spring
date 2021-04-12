package com.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String emailId;
    private String username;
    private String password;

    public UserEntity(String emailId, String username, String password) {
        this.emailId = emailId;
        this.username = username;
        this.password = password;
    }
}
