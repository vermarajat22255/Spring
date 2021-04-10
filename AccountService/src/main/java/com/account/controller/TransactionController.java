package com.account.controller;

import com.account.model.Transaction;
import com.account.model.TransactionEntity;
import com.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:Configurations.properties")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("${TransController.GetAllTransByUserId}")
    public List<Transaction> transactions(@PathVariable String userId){
        return transactionService.transactions(userId, null);
    }

    @GetMapping("${TransController.GetOneTransactionByUserId}")
    public Transaction transaction(@PathVariable(value = "userId") String userId, @PathVariable(value = "transId") String transId){
        return transactionService.transactions(userId, transId).get(0);
    }
    @PostMapping("${TransController.CreateTransactionByUserId}")
    public Transaction createTransaction(@PathVariable String userId, @RequestBody Transaction transaction) throws Exception{
        return transactionService.createTransaction(userId, transaction);
    }
}
