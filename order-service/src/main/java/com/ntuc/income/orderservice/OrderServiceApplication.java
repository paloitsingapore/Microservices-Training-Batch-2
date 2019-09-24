package com.ntuc.income.orderservice;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding(Source.class)
@RestController
public class OrderServiceApplication {

  @Autowired Source source;

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

  @GetMapping("/event")
  public ResponseEntity orderEvent(){

    Message<String> event = MessageBuilder
        .withPayload(UUID
            .randomUUID()
            .toString())
        .build();
    source.output().send(event);
    return ResponseEntity.accepted().build();
  }


}
