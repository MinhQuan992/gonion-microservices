package com.gonion.fraud.service;

import com.gonion.fraud.entity.FraudCheckHistory;
import com.gonion.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  public boolean isFraudulentCustomer(Integer customerId) {
    var fraudCheck = FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .createdAt(LocalDateTime.now())
            .build();
    fraudCheckHistoryRepository.save(fraudCheck);
    return false;
  }
}
