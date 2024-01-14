package com.gonion.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.gonion.customer",
                "com.gonion.amqp"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.gonion.clients"
)
public class CustomerServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceApplication.class, args);
  }
}
