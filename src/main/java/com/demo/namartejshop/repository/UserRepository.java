package com.demo.namartejshop.repository;

import com.demo.namartejshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // registro: Comprobar si un username o email esta ocupado
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

   // LOGIN: recuperar el user
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}