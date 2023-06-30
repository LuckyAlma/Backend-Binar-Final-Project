package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.repository.CustomersRepository;
import com.binar.pemesanantiketpesawat.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @PostMapping
    public ResponseEntity<MessageModel> saveCustomers(@RequestBody Customers customersRequest) {
        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.saveCustomers(customersRequest);
        if (customersRequest == null) {
            messageModel.setMessage("failed to save");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setMessage("success save data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);
            return ResponseEntity.ok(messageModel);
        }
    }

    @PutMapping
    public ResponseEntity<MessageModel> updateCustomer(@RequestBody Customers customersRequest) {
        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.updateCustomersById(customersRequest);
        if (customersRequest == null) {
            messageModel.setMessage("failed to save");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setMessage("success save data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);
            return ResponseEntity.ok(messageModel);
        }
    }

    @DeleteMapping
    public ResponseEntity<MessageModel>  deleteCustomers(@RequestParam Integer idRequest) {
        MessageModel messageModel = new MessageModel();
        String customersResponse = customersService.deleteCustomers(idRequest);
        if (customersResponse == null) {
            messageModel.setMessage("failed to delete customers");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setMessage("success save data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);
            return ResponseEntity.ok(messageModel);
        }
    }

    @GetMapping
    public ResponseEntity<MessageModel> getCustomersById(@RequestParam Integer idRequest) {
        MessageModel messageModel = new MessageModel();
        Customers customersResponse = customersService.findByIdCustomers(idRequest);
        if (customersResponse == null) {
            messageModel.setMessage("failed to get customer by id " + idRequest);
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setMessage("success save data");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(customersResponse);
            return ResponseEntity.ok(messageModel);
        }
    }
}
