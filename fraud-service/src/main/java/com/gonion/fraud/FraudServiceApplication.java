package com.gonion.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FraudServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(FraudServiceApplication.class, args);
  }
}
