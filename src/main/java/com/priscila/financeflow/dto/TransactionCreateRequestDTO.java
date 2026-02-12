package com.priscila.financeflow.dto;

import com.priscila.financeflow.model.TransactionType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class TransactionCreateRequestDTO {

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must have at most 255 characters")
    private String description;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    @NotNull(message = "Transaction type is required")
    private TransactionType type;
    @NotNull(message = "Account id is required")
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
