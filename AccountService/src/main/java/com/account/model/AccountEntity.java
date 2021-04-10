package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    private long id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "USER_ID")
//    private UserEntity userEntity;
    private float balance;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    List<TransactionEntity> transactions;
}
