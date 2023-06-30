package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CustomersControllerTest {

    @Mock
    private CustomersService customersService;

    @InjectMocks
    private CustomersController customersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCustomers_Success() {
        // Arrange
        Customers customersRequest = new Customers();
        customersRequest.setFullName("Aditya Prabowo");
        customersRequest.setEmail("aditya@gmail.com");

        Customers customersResponse = new Customers();
        customersResponse.setCustomerId(1);
        customersResponse.setFullName("Aditya Prabowo");
        customersResponse.setEmail("aditya@gmail.com");

        when(customersService.saveCustomers(customersRequest)).thenReturn(customersResponse);

        // Act
        ResponseEntity<MessageModel> response = customersController.saveCustomers(customersRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success save data", response.getBody().getMessage());
        assertEquals(customersResponse, response.getBody().getData());
    }

    @Test
    public void testSaveCustomers_Failure() {
        // Arrange
        Customers customersRequest = null;

        // Act
        ResponseEntity<MessageModel> response = customersController.saveCustomers(customersRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("failed to save", response.getBody().getMessage());
    }

    @Test
    public void testUpdateCustomer_Success() {
        // Arrange
        Customers customersRequest = new Customers();
        customersRequest.setCustomerId(1);
        customersRequest.setFullName("Aditya Prabowo");
        customersRequest.setEmail("aditya@gmail.com");

        Customers customersResponse = new Customers();
        customersResponse.setCustomerId(1);
        customersRequest.setFullName("Aditya Prabowo");
        customersRequest.setEmail("aditya@gmail.com");

        when(customersService.updateCustomersById(customersRequest)).thenReturn(customersResponse);

        // Act
        ResponseEntity<MessageModel> response = customersController.updateCustomer(customersRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success save data", response.getBody().getMessage());
        assertEquals(customersResponse, response.getBody().getData());
    }

    @Test
    public void testUpdateCustomer_Failure() {
        // Arrange
        Customers customersRequest = null;

        // Act
        ResponseEntity<MessageModel> response = customersController.updateCustomer(customersRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("failed to save", response.getBody().getMessage());
    }

    @Test
    public void testDeleteCustomers_Success() {
        // Arrange
        Integer idRequest = 1;
        String customersResponse = "Customer deleted successfully";

        when(customersService.deleteCustomers(idRequest)).thenReturn(customersResponse);

        // Act
        ResponseEntity<MessageModel> response = customersController.deleteCustomers(idRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success save data", response.getBody().getMessage());
        assertEquals(customersResponse, response.getBody().getData());
    }

    @Test
    public void testDeleteCustomers_Failure() {
        // Arrange
        Integer idRequest = null;

        // Act
        ResponseEntity<MessageModel> response = customersController.deleteCustomers(idRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("failed to delete customers", response.getBody().getMessage());
    }

    @Test
    public void testGetCustomersById_Success() {
        // Arrange
        Integer idRequest = 1;

        Customers customersResponse = new Customers();
        customersResponse.setCustomerId(1);
        customersResponse.setFullName("Aditya Prabowo");
        customersResponse.setEmail("aditya@gmail.com");

        when(customersService.findByIdCustomers(idRequest)).thenReturn(customersResponse);

        // Act
        ResponseEntity<MessageModel> response = customersController.getCustomersById(idRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success save data", response.getBody().getMessage());
        assertEquals(customersResponse, response.getBody().getData());
    }

    @Test
    public void testGetCustomersById_Failure() {
        // Arrange
        Integer idRequest = null;

        // Act
        ResponseEntity<MessageModel> response = customersController.getCustomersById(idRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("failed to get customer by id " + idRequest, response.getBody().getMessage());
    }
}
