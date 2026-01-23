package com.priscila.financeflow.service;

import com.priscila.financeflow.dto.TransactionCreateRequestDTO;
import com.priscila.financeflow.model.Account;
import com.priscila.financeflow.model.TransactionType;
import com.priscila.financeflow.repository.AccountRepository;
import com.priscila.financeflow.repository.TransactionRepository;
import com.priscila.financeflow.model.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.xa.XAResource;
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

    public Transaction create(TransactionCreateRequestDTO dto) {

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

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

        accountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> listByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
