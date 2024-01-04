package com.gonion.notification.service;

import com.gonion.notification.entity.Notification;
import com.gonion.notification.repository.NotificationRepository;
import com.gonion.notification.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public void send(NotificationRequest notificationRequest) {
    var notification = Notification.builder()
            .toCustomerId(notificationRequest.toCustomerId())
            .toCustomerEmail(notificationRequest.toCustomerEmail())
            .sender("gonion")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build();
    notificationRepository.save(notification);
  }
}
