package org.piggybank.controller;

import org.piggybank.model.TransactionDTO;
import org.piggybank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long transactionId, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transactionId, transactionDTO);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") Long transactionId) {
        TransactionDTO transactionDTO = transactionService.findTransactionById(transactionId);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable("accountId") Long accountId) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByAccountId(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByType(@PathVariable("type") String transactionType) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByType(transactionType);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/type/{type}/paged")
    public ResponseEntity<Page<TransactionDTO>> getTransactionsByTypeWithPagination(@PathVariable("type") String transactionType, Pageable pageable) {
        Page<TransactionDTO> transactions = transactionService.findTransactionsByTypeWithPagination(transactionType, pageable);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TransactionDTO>> getAllActiveTransactions() {
        List<TransactionDTO> transactions = transactionService.findAllActiveTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/soft")
    public ResponseEntity<Void> softDeleteTransaction(@PathVariable("id") Long transactionId) {
        transactionService.softDeleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestParam Long accountId, @RequestParam Double amount, @RequestParam String reason) {
        TransactionDTO transactionDTO = transactionService.withdraw(accountId, amount, reason);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestParam Long accountId, @RequestParam Double amount, @RequestParam String reason) {
        TransactionDTO transactionDTO = transactionService.deposit(accountId, amount, reason);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestParam Long sourceAccountId, @RequestParam Long destinationAccountId, @RequestParam Double amount, @RequestParam String reason) {
        transactionService.transfer(sourceAccountId, destinationAccountId, amount, reason);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
