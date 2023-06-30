package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Customers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomersService {

    Customers saveCustomers(Customers customersRequest);

    Customers updateCustomersById(Customers customersRequest);

    Customers findByIdCustomers(Integer idRequest);

    List<Customers> getAllCustomers();

    String deleteCustomers(Integer idRequest);


}
