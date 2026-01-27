package com.priscila.financeflow.dto;

import com.priscila.financeflow.model.TransactionType;

import java.math.BigDecimal;

public class TransactionCreateRequestDTO {

    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private Long accountId;

    public TransactionCreateRequestDTO(Long accountId, String description, BigDecimal amount, TransactionType type) {
        this.accountId = accountId;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // (ajuda o spring/Jackson)
    public TransactionCreateRequestDTO() {
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

    public Long getAccountId() {
        return accountId;
    }
}
