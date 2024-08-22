package com.vani.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vani.ams.model.AccountDTO;
@Service
public interface AccountService<AccountType, AccoutDTO> {
	List<AccountDTO> findAllAccounts();
	AccoutDTO findAccountById(Long id);
	AccountDTO createAccount(AccountDTO accountDTO);
	AccountDTO updateAccount(Long id,AccountDTO accountDTO);
	String deleteAccount(Long id);
	List<AccountDTO> findAccountsByType(AccountType accountType);
	Optional<AccountDTO> findAccountByAccountNumber(String accountNumber);
}
