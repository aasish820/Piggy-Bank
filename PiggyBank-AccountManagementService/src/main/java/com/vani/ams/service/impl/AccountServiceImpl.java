package com.vani.ams.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vani.ams.entity.Account;
import com.vani.ams.entity.AccountType;
import com.vani.ams.exceptions.AccountNotFoundException;
import com.vani.ams.model.AccountDTO;
import com.vani.ams.repo.AccountRepo;
import com.vani.ams.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepo repo;
	
	public AccountDTO convertToDTO(Account account) {
	    AccountDTO accountDTO = new AccountDTO();
	    accountDTO.setId(account.getId());
	    accountDTO.setAccountNumber(account.getAccountNumber());
	    accountDTO.setAccountHolderName(account.getAccountHolderName());
	    accountDTO.setAccountType(
	            account.getAccountType() != null ? account.getAccountType().name() : null
	        );
	    accountDTO.setEmail(account.getEmail());
	    accountDTO.setPhoneNumber(account.getPhoneNumber());
	    accountDTO.setBalance(account.getBalance());
	    accountDTO.setCreationDate(account.getCreationDate());
	    accountDTO.setLastUpdatedDate(account.getLastUpdatedDate());
	    accountDTO.setUserId(account.getUserId());

	    return accountDTO;
	    
	}


	@Override
	public List<AccountDTO> findAllAccounts() {
		List<Account> accountList = repo.findAllActiveAccounts();
        return accountList.stream().map(account->convertToDTO(account)).collect(Collectors.toList());
	}

	@Override
	public AccountDTO findAccountById(Long id) {
	    Optional<Account> accountOpt = repo.findActiveAccountById(id);
	    Account account = accountOpt.orElseThrow(() -> new AccountNotFoundException("Account does not exist"));
	    return convertToDTO(account);
	}

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		System.out.println(accountDTO.getAccountType());
		System.out.println(accountDTO.getAccountNumber());

		System.out.println(accountDTO.getAccountHolderName());

		System.out.println(accountDTO.getEmail());

		System.out.println(accountDTO.getPhoneNumber());
		
		
		Account account = new Account();
	    account.setAccountNumber(accountDTO.getAccountNumber());
	    account.setAccountHolderName(accountDTO.getAccountHolderName());
	    account.setAccountType(AccountType.valueOf(accountDTO.getAccountType())); // Convert String to Enum
	    account.setEmail(accountDTO.getEmail());
	    account.setPhoneNumber(accountDTO.getPhoneNumber());
	    account.setBalance(accountDTO.getBalance());
	    account.setCreationDate(accountDTO.getCreationDate());
	    account.setLastUpdatedDate(accountDTO.getLastUpdatedDate());
	    account.setUserId(accountDTO.getUserId());
	    
	    Account savedAccount =(Account) repo.save(account);
	    return convertToDTO(savedAccount);
	}

	@Override
	public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account accountObj = new Account();

        accountObj.setAccountNumber(accountDTO.getAccountNumber());
        accountObj.setAccountHolderName(accountDTO.getAccountHolderName());
        accountObj.setAccountType(AccountType.valueOf(accountDTO.getAccountType())); // Convert String to Enum
        accountObj.setEmail(accountDTO.getEmail());
        accountObj.setPhoneNumber(accountDTO.getPhoneNumber());
        accountObj.setBalance(accountDTO.getBalance());
        accountObj.setCreationDate(accountDTO.getCreationDate());
        accountObj.setLastUpdatedDate(accountDTO.getLastUpdatedDate());
        accountObj.setUserId(accountDTO.getUserId());

        Account updatedAccount = (Account) repo.save(accountObj);
        return convertToDTO(updatedAccount);
    }

	@Override
	public String deleteAccount(Long id) {
		 String msg = "Account deleted successfully";
		    Account accountObj = (Account) repo.findActiveAccountsByType(id).get(0);
		    if(accountObj != null) {
		        repo.softDeletePostById(id);
		    } else {
		        throw new AccountNotFoundException("Account does not exist");
		    }
		    return msg;
	}

	@Override
	public List<AccountDTO> findAccountsByType(Object accountType) {
        AccountType type;
        if (accountType instanceof String) {
            type = AccountType.valueOf((String) accountType); // Convert String to Enum
        } else if (accountType instanceof AccountType) {
            type = (AccountType) accountType;
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        List<Account> accounts = repo.findActiveAccountsByType(type);
        return accounts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
	@Override
	public Optional<AccountDTO> findAccountByAccountNumber(String accountNumber) {
        Optional<Account> accountOpt = repo.findActiveAccountByAccountNumber(accountNumber);
        return accountOpt.map(this::convertToDTO);
    }
}
	