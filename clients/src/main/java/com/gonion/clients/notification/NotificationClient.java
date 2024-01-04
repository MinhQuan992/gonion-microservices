package com.gonion.clients.notification;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification-service")
public interface NotificationClient {
  @PostMapping("api/v1/notifications")
  void sendNotification(@RequestBody NotificationRequest request);
}
