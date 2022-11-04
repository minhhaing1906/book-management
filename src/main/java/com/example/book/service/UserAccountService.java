package com.example.book.service;

import com.example.book.entities.UserAccount;
import com.example.book.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    @Transactional
    public Optional<UserAccount> findByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }
    @Transactional
    public Optional<UserAccount> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public boolean checkUserName(String userName) {
        return findByUserName(userName).isPresent();
    }

    public boolean checkEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
