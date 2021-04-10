package com.account.service;

import com.account.model.AccountEntity;
import com.account.model.Transaction;
import com.account.model.TransactionEntity;
import com.account.model.UserEntity;
import com.account.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Transaction> transactions(String userId, String transId) {
        List<Transaction> transactions = new ArrayList<>();
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        List<TransactionEntity> transactionEntities = userEntity.getAccountEntity().getTransactions();

        if(transId != null){
            return transactionEntities.stream().filter(te -> te.getTransId().equals(transId)).map(te -> modelMapper.map(te, Transaction.class)).collect(Collectors.toList());
        }else {
            for (TransactionEntity te : transactionEntities) {
                transactions.add(modelMapper.map(te, Transaction.class));
            }
        }
//        return transactionEntities.stream().map(te -> modelMapper.map(te, Transaction.class)).collect(Collectors.toList());
        return transactions;
    }

    @Override
    @Transactional
    public Transaction createTransaction(String userId, Transaction transaction) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        transaction.setTransId(UUID.randomUUID().toString());
        transaction.setTransDate(new Date());
        transaction.setStatus("Success");
        TransactionEntity transactionEntity = modelMapper.map(transaction, TransactionEntity.class);
        userEntity.getAccountEntity().getTransactions().add(transactionEntity);
        userEntity.getAccountEntity().setBalance(userEntity.getAccountEntity().getBalance() + transaction.getAmount());
        userRepository.save(userEntity);
        throw new Exception("new exception");
//        return transaction;
    }
}
