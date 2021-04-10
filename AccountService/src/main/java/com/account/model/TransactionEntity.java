package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TransactionEntity {
    @Id
    @Column
    private String transId;
    @Column
    private float amount;
    @Column
    private Date transDate;
    @Column
    private String status;
    @Column
    private String currency;
}
