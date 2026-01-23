package com.priscila.financeflow.service;

import com.priscila.financeflow.model.Account;
import com.priscila.financeflow.model.User;
import com.priscila.financeflow.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String name, User user) {
        Account account = new Account();
        account.setName(name);
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);
        account.setCreatedAt(java.time.LocalDateTime.now());

        return accountRepository.save(account);
    }

    public List<Account> listAccounts(User user) {
        return accountRepository.findByUserId(user.getId());
    }
}
