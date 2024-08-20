package com.vani.loans.model;

import com.vani.loans.entity.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

	private Long id;
	private String borrowerName;
	private Double loanAmount;
	private Double interestRate;
	private Integer loanTerm;
	private String status;

	public String getStatus() {
		return this.status;
	}

	public Loan orElseThrow(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("This is invalid");
        }
        return loan;
    }

	
}