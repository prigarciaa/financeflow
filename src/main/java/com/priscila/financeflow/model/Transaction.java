package com.priscila.financeflow.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction() {
    }

    public Transaction(String description, BigDecimal amount, TransactionType type, Account account) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.date = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

