package com.vani.ams.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vani.ams.entity.Account;

@Repository
public interface AccountRepo<AccountType> extends JpaRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.deleted_at IS NULL")
	List<Account> findAllActiveAccounts();

	@Query("SELECT a FROM Account a WHERE a.id = ?1 AND a.deleted_at IS NULL")
	Optional<Account> findActiveAccountById(Long id);

	@Query("SELECT a FROM Account a WHERE a.accountType = ?1 AND a.deleted_at IS NULL")
	List<Account> findActiveAccountsByType(AccountType accountType);

	@Query("SELECT a FROM Account a WHERE a.accountNumber = ?1 AND a.deleted_at IS NULL")
	Optional<Account> findActiveAccountByAccountNumber(String accountNumber);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Account a SET a.deleted_at = CURRENT_TIMESTAMP WHERE a.id = :id AND a.deleted_at IS NULL", nativeQuery = true)
	void softDeletePostById(Long id);

}
