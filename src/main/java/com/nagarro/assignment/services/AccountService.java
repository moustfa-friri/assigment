package com.nagarro.assignment.services;

import com.nagarro.assignment.dao.AccountRepository;
import com.nagarro.assignment.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }
}
