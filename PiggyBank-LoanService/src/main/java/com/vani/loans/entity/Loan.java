package com.vani.loans.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String borrowerName;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private Integer loanTerm;
    
    @Column(nullable = false)
    private String status;
    
	private Date deleted_at;
  
}
