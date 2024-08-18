package com.vani.loans.service;

import java.util.List;

import com.vani.loans.model.LoanDTO;

public interface LoanService {
	List<LoanDTO> getAllLoans();
    LoanDTO getLoanByID(Long id);
    LoanDTO createLoan(LoanDTO loanDTO);
    LoanDTO updateLoan(Long id, LoanDTO loanDTO);
    String deleteLoan(Long id);

}
