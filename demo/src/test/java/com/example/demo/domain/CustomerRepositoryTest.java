package com.example.demo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveAndFind() {
        Customer c = new Customer("Alice", "alice@local");
        Customer saved = customerRepository.save(c);

        assertThat(saved.getId()).isNotNull();

        Customer found = customerRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Alice");
    }
}
