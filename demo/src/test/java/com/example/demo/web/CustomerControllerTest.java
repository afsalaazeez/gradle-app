package com.example.demo.web;

import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void testGetAllCustomers() throws Exception {
        List<Customer> customers = List.of(new Customer("Alice", "alice@example.com"));
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer("Bob", "bob@example.com");
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer("Charlie", "charlie@example.com");
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "Charlie", "email": "charlie@example.com"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk());
    }
}
