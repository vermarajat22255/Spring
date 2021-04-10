package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Transaction {
    private String transId;
    private float amount;
    private Date transDate;
    private String status;
    private String currency;

}
