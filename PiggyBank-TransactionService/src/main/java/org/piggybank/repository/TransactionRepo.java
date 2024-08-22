package org.piggybank.repository;

import org.piggybank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId")
    List<Transaction> findTransactionsByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.transactionType = :transactionType")
    List<Transaction> findTransactionsByType(@Param("transactionType") String transactionType);

    @Query("SELECT t FROM Transaction t WHERE t.deleted_at IS NULL")
    List<Transaction> findAllActiveTransactions();

    @Modifying
    @Transactional
    @Query("UPDATE Transaction t SET t.deleted_at = :deletedAt WHERE t.transactionId = :transactionId")
    void softDeleteById(@Param("transactionId") Long transactionId, @Param("deletedAt") LocalDateTime deletedAt);

    @Query("SELECT t FROM Transaction t WHERE t.transactionType = :transactionType")
    Page<Transaction> findTransactionsByTypeWithPagination(@Param("transactionType") String transactionType, Pageable pageable);
}
