package com.priscila.financeflow.service;

import com.priscila.financeflow.dto.TransactionCreateRequestDTO;
import com.priscila.financeflow.model.Account;
import com.priscila.financeflow.model.Transaction;
import com.priscila.financeflow.model.TransactionType;
import com.priscila.financeflow.repository.AccountRepository;
import com.priscila.financeflow.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    // Mock = ator fingindo ser o repositório
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    // Classe real sendo testada, com os mocks injetados
    @InjectMocks
    private TransactionService transactionService;

@Test
void shouldCreateIncomeTransactionAndIncreaseBalance() {
    // Arrange (preparação)
    Long accountId = 1L;

    Account account = new Account();
    account.setId(accountId);
    account.setBalance(new BigDecimal("100.00"));

    TransactionCreateRequestDTO dto = new TransactionCreateRequestDTO(
            accountId,
            "Salário",
            new BigDecimal("50.00"),
            TransactionType.INCOME
    );

    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
    when(transactionRepository.save(any(Transaction.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    // Act (ação)
    Transaction result = transactionService.create(dto);

    // Assert (verificações)
    assertEquals(new BigDecimal("150.00"), account.getBalance());

    verify(accountRepository).save(account);
    verify(transactionRepository).save(any(Transaction.class));

}

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        Long accountId = 99L;

        TransactionCreateRequestDTO dto = new TransactionCreateRequestDTO(
                accountId,
                "Teste",
                new BigDecimal("10.00"),
                TransactionType.INCOME
        );

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> transactionService.create(dto)
        );

        assertEquals("Conta não encontrada", exception.getMessage());

        verify(transactionRepository, never()).save(any());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void shouldCreateExpenseTransactionAndDecreaseBalance() {
        Long accountId = 1L;

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(new BigDecimal("100.00"));

        TransactionCreateRequestDTO dto = new TransactionCreateRequestDTO(
                accountId,
                "Mercado",
                new BigDecimal("40.00"),
                TransactionType.EXPENSE
        );

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        transactionService.create(dto);

        // Assert
        assertEquals(new BigDecimal("60.00"), account.getBalance());

        verify(accountRepository).save(account);
        verify(transactionRepository).save(any(Transaction.class));
    }
}



