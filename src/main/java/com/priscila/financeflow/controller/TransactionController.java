package com.priscila.financeflow.controller;

import com.priscila.financeflow.dto.TransactionCreateRequestDTO;
import com.priscila.financeflow.model.Transaction;
import com.priscila.financeflow.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping
    public List<Transaction> list(
            @RequestParam Long accountId,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return transactionService.list(accountId, startDate, endDate, sortBy, direction);
    }

    // Listagem com paginação
    @GetMapping("/paged")
    public Page<Transaction> listPaged(
            @RequestParam Long accountId,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortby,
            @RequestParam(defaultValue = "desc") String direction
    ) {

        return transactionService.listPaged(
                accountId,
                startDate,
                endDate,
                page,
                size,
                sortby,
                direction
        );
    }
}
