package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.ERole;
import com.binar.pemesanantiketpesawat.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}