package com.gonion.customer.controller;

import com.gonion.customer.request.CustomerRegistrationRequest;
import com.gonion.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("customers")
public record CustomerController(CustomerService customerService) {
  @PostMapping
  public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
    log.info("New customer registration: {}", request);
    customerService.registerCustomer(request);
  }
}
