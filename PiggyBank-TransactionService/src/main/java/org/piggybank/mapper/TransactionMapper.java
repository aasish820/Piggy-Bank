package org.piggybank.mapper;

import org.piggybank.entity.Transaction;
import org.piggybank.entity.TransactionReason;
import org.piggybank.entity.TransactionType;
import org.piggybank.model.TransactionDTO;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
            transaction.getTransactionId(),
            transaction.getTransactionType().name(),
            transaction.getAmount(),
            transaction.getAccountId(),
            transaction.getTransactionReason().name()
        );
    }

    public Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionDTO.getAccountId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionType(TransactionType.valueOf(transactionDTO.getTransactionType()));
        transaction.setTransactionReason(TransactionReason.valueOf(transactionDTO.getTransactionReason()));
        return transaction;
    }
}
