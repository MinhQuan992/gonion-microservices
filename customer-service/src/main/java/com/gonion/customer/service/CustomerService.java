package com.gonion.customer.service;

import com.gonion.amqp.RabbitMQMessageProducer;
import com.gonion.clients.fraud.FraudClient;
import com.gonion.clients.notification.NotificationRequest;
import com.gonion.customer.entity.Customer;
import com.gonion.customer.repository.CustomerRepository;
import com.gonion.customer.request.CustomerRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer producer;

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    customerRepository.saveAndFlush(customer);

    var fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    assert fraudCheckResponse != null;
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster");
    }

    NotificationRequest notificationRequest = new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, welcome to Gonion...",
                    customer.getFirstName())
    );
    // TODO: Don't hardcode like this
    producer.publish(
            notificationRequest,
            "internal.exchange",
            "internal.notification.routing-key"
    );
  }
}
