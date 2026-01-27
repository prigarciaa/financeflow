package com.priscila.financeflow.repository;

import com.priscila.financeflow.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAccountIdAndDateBetween(
            Long accountId,
            LocalDateTime start,
            LocalDateTime end,
            Sort sort
    );

    List<Transaction> findByAccountId(Long accountId, Sort sort);

    Page<Transaction> findByAccountId(Long accountId, Pageable pageable);

    Page<Transaction> findByAccountIdAndDateBetween(
            Long accountId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );
}

