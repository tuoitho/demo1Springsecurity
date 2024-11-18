package com.demospringsecurity.controller;

import com.demospringsecurity.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").name("Nguyễn Hữu Trung").email("c1@gmail.com").build(),
            Customer.builder().id("002").name("NHT").email("c2@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }


    @GetMapping("/customer/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Customer> getCustomerList() {
        List<Customer> customers = this.customers;
        return ResponseEntity.ok(customers.getFirst());
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
        return ResponseEntity.ok(customers.getFirst());
    }
}