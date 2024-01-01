package com.gonion.customer.service;

import com.gonion.customer.entity.Customer;
import com.gonion.customer.repository.CustomerRepository;
import com.gonion.customer.request.CustomerRegistrationRequest;
import com.gonion.customer.response.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final RestTemplate restTemplate;

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    customerRepository.saveAndFlush(customer);

    var fraudCheckResponse = restTemplate.getForObject(
            "http://FRAUD-SERVICE/api/v1/fraud-check/{customerId}",
            FraudCheckResponse.class,
            customer.getId()
    );

    assert fraudCheckResponse != null;
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster");
    }
  }
}
