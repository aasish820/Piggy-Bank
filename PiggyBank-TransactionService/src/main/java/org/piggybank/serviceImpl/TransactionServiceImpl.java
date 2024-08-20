package org.piggybank.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.piggybank.entity.Transaction;
import org.piggybank.entity.TransactionReason;
import org.piggybank.entity.TransactionType;
import org.piggybank.exception.TransactionNotFoundException;
import org.piggybank.exception.InsufficientFundsException;
import org.piggybank.mapper.TransactionMapper;
import org.piggybank.model.TransactionDTO;
import org.piggybank.repository.TransactionRepo;
import org.piggybank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionMapper transactionMapper;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String ACCOUNT_SERVICE_URL = "http://account-service/api/accounts/";

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction = transactionRepo.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public TransactionDTO updateTransaction(Long transactionId, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id " + transactionId));

        transaction.setTransactionType(TransactionType.valueOf(transactionDTO.getTransactionType()));
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionReason(TransactionReason.valueOf(transactionDTO.getTransactionReason()));

        transaction = transactionRepo.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public TransactionDTO findTransactionById(Long transactionId) {
        return transactionRepo.findById(transactionId)
                .map(transactionMapper::toDTO)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id " + transactionId));
    }

    @Override
    public List<TransactionDTO> findTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepo.findTransactionsByAccountId(accountId);
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(transactionMapper.toDTO(transaction));
        }

        return transactionDTOs;
    }

    @Override
    public List<TransactionDTO> findTransactionsByType(String transactionType) {
        List<Transaction> transactions = transactionRepo.findTransactionsByType(transactionType);
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(transactionMapper.toDTO(transaction));
        }

        return transactionDTOs;
    }

    @Override
    public Page<TransactionDTO> findTransactionsByTypeWithPagination(String transactionType, Pageable pageable) {
        return transactionRepo.findTransactionsByTypeWithPagination(transactionType, pageable)
                .map(transactionMapper::toDTO);
    }

    @Override
    public List<TransactionDTO> findAllActiveTransactions() {
        List<Transaction> transactions = transactionRepo.findAllActiveTransactions();
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(transactionMapper.toDTO(transaction));
        }

        return transactionDTOs;
    }

    @Override
    public void softDeleteTransaction(Long transactionId) {
        transactionRepo.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id " + transactionId));
        transactionRepo.softDeleteById(transactionId, LocalDateTime.now());
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepo.deleteById(transactionId);
    }

    @Override
    public TransactionDTO withdraw(Long accountId, Double amount, String reason) {
    	 @SuppressWarnings("unchecked")
		Map<String, Object> accountData = restTemplate.getForObject(ACCOUNT_SERVICE_URL + accountId, Map.class);

         Double balance = (Double) accountData.get("balance");

         if (balance < amount) {
             throw new InsufficientFundsException("Insufficient balance for withdrawal");
         }

         balance -= amount;

         Transaction transaction = new Transaction();
         transaction.setAccountId(accountId);
         transaction.setAmount(amount);
         transaction.setTransactionType(TransactionType.WITHDRAWAL);
         transaction.setTransactionReason(TransactionReason.valueOf(reason));

         // Save the transaction
         transaction = transactionRepo.save(transaction);

         // Return the DTO
         return transactionMapper.toDTO(transaction);
    }

    @SuppressWarnings("unchecked")
	@Override
    public TransactionDTO deposit(Long accountId, Double amount, String reason) {
        Map<String, Object> accountData = restTemplate.getForObject(ACCOUNT_SERVICE_URL + accountId, Map.class);

        Double balance = (Double) accountData.get("balance");

        balance += amount;

        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionReason(TransactionReason.valueOf(reason));

        // Save the transaction
        transaction = transactionRepo.save(transaction);

        // Return the DTO
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public void transfer(Long sourceAccountId, Long destinationAccountId, Double amount, String reason) {
        // Withdraw from source account
        withdraw(sourceAccountId, amount, reason);

        // Deposit into destination account
        deposit(destinationAccountId, amount, reason);
    }
}
