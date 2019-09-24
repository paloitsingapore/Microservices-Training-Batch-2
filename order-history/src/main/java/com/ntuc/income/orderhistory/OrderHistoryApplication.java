package com.ntuc.income.orderhistory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@EnableBinding(Sink.class)
public class OrderHistoryApplication {

  private static Logger log = LoggerFactory.getLogger(OrderHistoryApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(OrderHistoryApplication.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void listen(@Payload String event){
    log.info("Event received {}", event);

  }
}
