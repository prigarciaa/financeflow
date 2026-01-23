package com.priscila.financeflow.controller;

import com.priscila.financeflow.dto.TransactionCreateRequestDTO;
import com.priscila.financeflow.model.Transaction;
import com.priscila.financeflow.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction create(@RequestBody TransactionCreateRequestDTO dto) {
        return transactionService.create(dto);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> listByAccount(@PathVariable Long accountId) {
        return transactionService.listByAccount(accountId);
    }
}
