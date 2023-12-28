package com.gonion.customer.service;

import com.gonion.customer.entity.Customer;
import com.gonion.customer.repository.CustomerRepository;
import com.gonion.customer.request.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    customerRepository.save(customer);
  }
}
