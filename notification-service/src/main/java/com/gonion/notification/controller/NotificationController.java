package com.gonion.notification.controller;

import com.gonion.clients.notification.NotificationRequest;
import com.gonion.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @PostMapping
  public void sendNotification(@RequestBody NotificationRequest request) {
    log.info("New notification sent: {}", request);
    notificationService.send(request);
  }
}
