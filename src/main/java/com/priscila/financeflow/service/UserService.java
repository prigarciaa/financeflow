package com.priscila.financeflow.service;

import com.priscila.financeflow.model.User;
import com.priscila.financeflow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public User create(User user) {
        return userRepository.save(user);
    }

    // READ - listar todos
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // READ - buscar por id
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // UPDATE
    public User update(Long id, User user) {
        User existing = findById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());

        return userRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        User existing = findById(id);
        userRepository.delete(existing);
    }
}
