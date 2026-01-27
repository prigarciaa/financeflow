package com.priscila.financeflow.service;

import com.priscila.financeflow.dto.TransactionCreateRequestDTO;
import com.priscila.financeflow.model.Account;
import com.priscila.financeflow.model.TransactionType;
import com.priscila.financeflow.repository.AccountRepository;
import com.priscila.financeflow.repository.TransactionRepository;
import com.priscila.financeflow.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction create(TransactionCreateRequestDTO dto) {

        // Busca a Conta
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // Cria a transação
        Transaction transaction = new Transaction(
                dto.getDescription(),
                dto.getAmount(),
                dto.getType(),
                account
        );

        // Atualizar Saldo
        if (dto.getType() == TransactionType.INCOME) {
            account.setBalance(account.getBalance().add(dto.getAmount()));
        } else {
            account.setBalance(account.getBalance().subtract(dto.getAmount()));
        }

        // Salva
        accountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> list(
            Long accountId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String sortBy,
            String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);

        if (startDate != null && endDate != null) {
            return transactionRepository.findByAccountIdAndDateBetween(
                    accountId,
                    startDate,
                    endDate,
                    sort
            );
        }

        return transactionRepository.findByAccountId(accountId, sort);
    }

    public Page<Transaction> listPaged(
            Long accountId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int page,
            int size,
            String sortBy,
            String direction
    ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(direction),sortBy)
        );

        if (startDate != null && endDate != null) {
            return transactionRepository.findByAccountIdAndDateBetween(
                    accountId,
                    startDate,
                    endDate,
                    pageable


            );
        }

        return transactionRepository.findByAccountId(accountId, pageable);
    }
}
