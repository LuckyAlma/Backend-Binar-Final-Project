package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    Customers findByCustomerId(Integer idRequest);

}
