package com.priscila.financeflow.dto;

import com.priscila.financeflow.model.TransactionType;

import java.math.BigDecimal;

public class TransactionCreateRequestDTO {

    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private Long accountId;

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
