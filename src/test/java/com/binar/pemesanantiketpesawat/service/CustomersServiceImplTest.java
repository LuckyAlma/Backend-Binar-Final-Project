package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.repository.CustomersRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.CustomersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CustomersServiceImplTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersServiceImpl customersService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCustomers() {
        // Arrange
        Customers customersRequest = createCustomers();
        when(customersRepository.save(any(Customers.class))).thenReturn(customersRequest);

        // Act
        Customers result = customersService.saveCustomers(customersRequest);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customersRequest, result);
        verify(customersRepository, times(1)).save(any(Customers.class));
    }

    @Test
    public void testFindByIdCustomers() {
        // Arrange
        Integer idRequest = 1;
        Customers customersResponse = createCustomers();
        when(customersRepository.findByCustomerId(eq(idRequest))).thenReturn(customersResponse);

        // Act
        Customers result = customersService.findByIdCustomers(idRequest);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customersResponse, result);
        verify(customersRepository, times(1)).findByCustomerId(eq(idRequest));
    }

    @Disabled
    @Test
    public void testUpdateCustomersById_NotFound() {
        // Arrange
        Customers customersRequest = createCustomers();

        when(customersRepository.findByCustomerId(eq(customersRequest.getCustomerId()))).thenReturn(customersRequest);

        // Act
        Customers result = customersService.updateCustomersById(customersRequest);

        // Assert
        Assertions.assertNull(result);
        verify(customersRepository, times(1)).findByCustomerId(eq(customersRequest.getCustomerId()));
        verify(customersRepository, never()).save(any(Customers.class));
    }




    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customers> customersResponse = new ArrayList<>();
        customersResponse.add(createCustomers());
        when(customersRepository.findAll()).thenReturn(customersResponse);

        // Act
        List<Customers> result = customersService.getAllCustomers();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customersResponse, result);
        verify(customersRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllCustomers_Empty() {
        // Arrange
        List<Customers> customersResponse = new ArrayList<>();
        when(customersRepository.findAll()).thenReturn(customersResponse);

        // Act
        List<Customers> result = customersService.getAllCustomers();

        // Assert
        Assertions.assertNull(result);
        verify(customersRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomers() {
        // Arrange
        Integer idRequest = 1;
        Optional<Customers> idResponse = Optional.of(createCustomers());
        when(customersRepository.findById(eq(idRequest))).thenReturn(idResponse);

        // Act
        String result = customersService.deleteCustomers(idRequest);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("customers with id " + idRequest + " successfully deleted..!", result);
        verify(customersRepository, times(1)).findById(eq(idRequest));
        verify(customersRepository, times(1)).deleteById(eq(idRequest));
    }

    @Test
    public void testDeleteCustomers_NotFound() {
        // Arrange
        Integer idRequest = 1;
        Optional<Customers> idResponse = Optional.empty();
        when(customersRepository.findById(eq(idRequest))).thenReturn(idResponse);

        // Act
        String result = customersService.deleteCustomers(idRequest);

        // Assert
        Assertions.assertNull(result);
        verify(customersRepository, times(1)).findById(eq(idRequest));
        verify(customersRepository, never()).deleteById(anyInt());
    }

    @Disabled
    @Test
    public void testUpdateCustomersById() {
        // Arrange
        Customers customersRequest = createCustomers();
        Customers customersResponse = createCustomers();
        customersResponse.setFullName("Updated Name");

        Customers updatedCustomers = createCustomers();
        updatedCustomers.setFullName("Updated Name");

        when(customersRepository.findByCustomerId(eq(customersRequest.getCustomerId()))).thenReturn(customersResponse);
        when(customersRepository.save(any(Customers.class))).thenReturn(updatedCustomers);

        // Act
        Customers result = customersService.updateCustomersById(customersRequest);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedCustomers, result);
        Assertions.assertEquals("Updated Name", result.getFullName());
        verify(customersRepository, times(1)).findByCustomerId(eq(customersRequest.getCustomerId()));
        verify(customersRepository, times(1)).save(any(Customers.class));
    }


    private Customers createCustomers() {
        Customers customers = new Customers();
        customers.setCustomerId(1);
        customers.setFullName("John Doe");
        customers.setFamilyName("Doe");
        customers.setPhoneNumber("123456789");
        customers.setEmail("john.doe@example.com");
        return customers;
    }
}
