package com.gonion.notification.rabbitmq;

import com.gonion.clients.notification.NotificationRequest;
import com.gonion.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
  private final NotificationService notificationService;

  @RabbitListener(queues = "${rabbitmq.queue.notification}")
  public void consume(NotificationRequest request) {
    log.info("Consumed {} from queue", request);
    notificationService.send(request);
  }
}
