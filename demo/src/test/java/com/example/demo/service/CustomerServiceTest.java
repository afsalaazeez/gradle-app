package com.example.demo.service;

import com.example.demo.domain.Customer;
import com.example.demo.domain.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(new Customer("Alice", "a@x.com")));
        List<Customer> result = customerService.getAllCustomers();
        assertThat(result).hasSize(1);
        verify(customerRepository).findAll();
    }

    @Test
    void testGetCustomerById() {
        Customer c = new Customer("Bob", "b@x.com");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(c));
        Optional<Customer> result = customerService.getCustomerById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Bob");
    }

    @Test
    void testCreateCustomer() {
        Customer c = new Customer("Charlie", "c@x.com");
        when(customerRepository.save(any(Customer.class))).thenReturn(c);
        Customer result = customerService.createCustomer(c);
        assertThat(result.getName()).isEqualTo("Charlie");
    }

    @Test
    void testDeleteCustomer() {
        customerService.deleteCustomer(1L);
        verify(customerRepository).deleteById(1L);
    }
}
