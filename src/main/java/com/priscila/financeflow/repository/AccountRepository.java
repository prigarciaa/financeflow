package com.priscila.financeflow.repository;


import com.priscila.financeflow.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Buscar todas as contas de um usuário
    List<Account> findByUserId(Long userId);
}
