package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByUuidUser(UUID uuidRequest);

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);

}
