package com.vani.loans.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vani.loans.entity.Loan;
import com.vani.loans.model.LoanDTO;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer> {
	@Query("SELECT l FROM Loan l WHERE l.deleted_at IS NULL")
    List<LoanDTO> findAllLoans();

    @Query("SELECT l FROM Loan l WHERE l.id = ?1 AND l.deleted_at IS NULL")
    LoanDTO findLoanById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Loan l WHERE l.id = :id")
    void deleteLoanById(@Param("id") Long id);

    @Query("SELECT l FROM Loan l WHERE l.id = ?1 AND l.deleted_at IS NULL")
    LoanDTO findById(@Param("id") Long id);

}


