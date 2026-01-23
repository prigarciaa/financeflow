package com.priscila.financeflow.controller;

import com.priscila.financeflow.model.Account;
import com.priscila.financeflow.model.User;
import com.priscila.financeflow.service.AccountService;
import com.priscila.financeflow.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    public Account createAccount(
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal String email
    ) {
        User user = userService.findByEmail(email);
        return accountService.createAccount(body.get("name"), user);
    }

    @GetMapping
    public List<Account> listAccounts(@AuthenticationPrincipal String email) {
        User user = userService.findByEmail(email);
        return accountService.listAccounts(user);
    }
}
