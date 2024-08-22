package com.vani.ams.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vani.ams.exceptions.AccountNotFoundException;
import com.vani.ams.model.AccountDTO;
import com.vani.ams.service.impl.AccountServiceImpl;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO) {
        try {
            AccountDTO savedAccount = accountService.createAccount(accountDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Account created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Account could not be created");
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountDTOs = accountService.findAllAccounts();
        return ResponseEntity.ok(accountDTOs);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<?> getAccountById(@PathVariable("id") Long id) {
        try {
            AccountDTO accountDTO = accountService.findAccountById(id);
            System.out.println(accountDTO.getAccountHolderName());
            return ResponseEntity.ok(accountDTO);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Account does not exist");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateAccount(@PathVariable("id") Long id, @Valid @ModelAttribute("accountDTO") AccountDTO accountDTO) {
        try {
            AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
            return ResponseEntity.ok("Account updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Account could not be updated");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
        try {
            String msg = accountService.deleteAccount(id);
            return ResponseEntity.ok(msg);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body("Account does not exist");
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AccountDTO>> getAccountsByType(@PathVariable("type") String type) {
        try {
            List<AccountDTO> accounts = accountService.findAccountsByType(type);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<?> getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        Optional<AccountDTO> accountDTO = accountService.findAccountByAccountNumber(accountNumber);
        return  ResponseEntity.status(404).body("Account not found");
    }
}

