package com.vani.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vani.loans.exception.LoanNotFoundException;
import com.vani.loans.model.LoanDTO;
import com.vani.loans.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") Long id) {
        try {
            LoanDTO loan = loanService.getLoanByID(id);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (LoanNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
        LoanDTO createdLoan = loanService.createLoan(loanDTO);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable("id") Long id, @RequestBody LoanDTO loanDTO) {
        try {
            LoanDTO updatedLoan = loanService.updateLoan(id, loanDTO);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (LoanNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable("id") Long id) {
        try {
            String response = loanService.deleteLoan(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (LoanNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

