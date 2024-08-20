package com.vani.loans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vani.loans.entity.Loan;
import com.vani.loans.exception.LoanNotFoundException;
import com.vani.loans.model.LoanDTO;
import com.vani.loans.repo.LoanRepo;
import com.vani.loans.service.LoanService;
@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
    private LoanRepo loanRepo;

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepo.findAllLoans();
    }



    @Override
    public LoanDTO getLoanByID(Long id) {
        LoanDTO loanDTO = loanRepo.findLoanById(id);
        if (loanDTO == null) {
            throw new LoanNotFoundException("Loan with ID " + id + " not found");
        }
        return loanDTO;
    }


    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setBorrowerName(loanDTO.getBorrowerName());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setLoanTerm(loanDTO.getLoanTerm());
        loan.setStatus(loanDTO.getStatus());

        Loan savedLoan = loanRepo.save(loan);

        return new LoanDTO(savedLoan.getId(), savedLoan.getBorrowerName(), savedLoan.getLoanAmount(),
                savedLoan.getInterestRate(), savedLoan.getLoanTerm(), savedLoan.getStatus());
    }


    @Override
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
    	Loan existingLoan = loanRepo.findById(id).orElseThrow(null);

        if (existingLoan == null) {
            throw new LoanNotFoundException("Loan with ID " + id + " not found");
        }

        existingLoan.setBorrowerName(loanDTO.getBorrowerName());
        existingLoan.setLoanAmount(loanDTO.getLoanAmount());
        existingLoan.setInterestRate(loanDTO.getInterestRate());
        existingLoan.setLoanTerm(loanDTO.getLoanTerm());
        existingLoan.setStatus(loanDTO.getStatus());

        Loan updatedLoan = loanRepo.save(existingLoan);

        return new LoanDTO(updatedLoan.getId(), updatedLoan.getBorrowerName(), updatedLoan.getLoanAmount(),
                updatedLoan.getInterestRate(), updatedLoan.getLoanTerm(), updatedLoan.getStatus());
    }


	@Override
    public String deleteLoan(Long id) {
        LoanDTO loanDTO = loanRepo.findLoanById(id);
        if (loanDTO == null) {
            throw new LoanNotFoundException("Loan with ID " + id + " not found");
        }
        loanRepo.deleteLoanById(id);
        return "Loan deleted successfully";

	}

}
