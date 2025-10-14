package com.example.demo.web;

import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{id}")
  public Optional<Customer> getCustomerById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerService.createCustomer(customer);
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
  }
}
