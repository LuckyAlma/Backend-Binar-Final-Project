package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.repository.CustomersRepository;
import com.binar.pemesanantiketpesawat.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public Customers saveCustomers(Customers customersRequest) {
        return customersRepository.save(customersRequest);
    }

    @Override
    public Customers findByIdCustomers(Integer idRequest) {
        Customers customerResponse = customersRepository.findByCustomerId(idRequest);
        if (customerResponse.getCustomerId() == null) return null;
        return customerResponse;
    }

    @Override
    public List<Customers> getAllCustomers() {
        List<Customers> customersResponse = customersRepository.findAll();
        if (customersResponse.isEmpty()) return null;
        return customersResponse;
    }

    @Override
    public String deleteCustomers(Integer idRequest) {
        Optional<Customers> idResponse = customersRepository.findById(idRequest);
        if (idResponse.isEmpty()) return null;
        else {
            customersRepository.deleteById(idRequest);
            return "customers with id " + idRequest + " successfully deleted..!" ;
        }
    }

    @Override
    public Customers updateCustomersById(Customers customersRequest) {
        Customers customersResponse = customersRepository.findByCustomerId(customersRequest.getCustomerId());
        if (customersResponse == null) return null;
        else {
            customersResponse.setFullName(customersRequest.getFullName());
            customersResponse.setFamilyName(customersRequest.getFamilyName());
            customersResponse.setPhoneNumber(customersRequest.getPhoneNumber());
            customersResponse.setEmail(customersRequest.getEmail());
            customersRepository.save(customersResponse);
            return customersResponse;
        }
    }
}
