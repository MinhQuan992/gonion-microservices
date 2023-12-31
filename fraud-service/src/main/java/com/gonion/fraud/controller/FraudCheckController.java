package com.gonion.fraud.controller;

import com.gonion.fraud.response.FraudCheckResponse;
import com.gonion.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {
  private final FraudCheckService fraudCheckService;

  @GetMapping("{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    log.info("Fraud check request for customer {}", customerId);
    boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
