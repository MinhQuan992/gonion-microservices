package com.gonion.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.gonion.notification",
                "com.gonion.amqp"
        }
)
@EnableDiscoveryClient
public class NotificationServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }
}
