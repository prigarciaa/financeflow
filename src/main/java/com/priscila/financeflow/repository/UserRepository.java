package com.priscila.financeflow.repository;

import com.priscila.financeflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
