package org.piggybank.service;

import org.piggybank.model.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionDTO transactionDTO);

    TransactionDTO updateTransaction(Long transactionId, TransactionDTO transactionDTO);

    TransactionDTO findTransactionById(Long transactionId);

    List<TransactionDTO> findTransactionsByAccountId(Long accountId);

    List<TransactionDTO> findTransactionsByType(String transactionType);

    Page<TransactionDTO> findTransactionsByTypeWithPagination(String transactionType, Pageable pageable);

    List<TransactionDTO> findAllActiveTransactions();
    
    TransactionDTO withdraw(Long accountId, Double amount, String reason);

    TransactionDTO deposit(Long accountId, Double amount, String reason);

    void transfer(Long sourceAccountId, Long destinationAccountId, Double amount, String reason);

    void softDeleteTransaction(Long transactionId);

    void deleteTransaction(Long transactionId);
}
