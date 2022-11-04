package com.example.book.service;

import com.example.book.entities.UserAccount;
import com.example.book.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    private final UserAccountRepository accountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UserAccount save(UserAccount userAccount) {
        return accountRepository.save(userAccount);
    }
}
