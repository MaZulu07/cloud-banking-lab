package com.bank.account_service.service;

import com.bank.account_service.model.Account;
import com.bank.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // CREATE
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    // READ all
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // READ by ID
    public Account getAccountById(Long id) {
        Optional<Account> accountOpt = repository.findById(id);
        return accountOpt.orElse(null);
    }

    // UPDATE
    public Account updateAccount(Long id, Account updatedAccount) {
        Optional<Account> accountOpt = repository.findById(id);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setName(updatedAccount.getName());
            account.setEmail(updatedAccount.getEmail());
            account.setAddress(updatedAccount.getAddress());
            account.setContactNumber(updatedAccount.getContactNumber());
            return repository.save(account);
        } else {
            return null;
        }
    }

    // DELETE
    public boolean deleteAccount(Long id) {
        Optional<Account> accountOpt = repository.findById(id);
        if (accountOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}