package com.gonion.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud-service")
public interface FraudClient {
  @GetMapping("api/v1/fraud-check/{customerId}")
  FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
