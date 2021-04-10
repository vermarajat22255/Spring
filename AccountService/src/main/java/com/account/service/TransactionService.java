package com.account.service;

import com.account.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<Transaction> transactions(String userId, String transId);
    Transaction createTransaction(String userId, Transaction transaction) throws Exception;
}
