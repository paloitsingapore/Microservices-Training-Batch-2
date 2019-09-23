package com.ntuc.income;

import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PaymentApplication {

  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class, args);
  }

  @Bean
  public Function<Flux<String>, Flux<String>> greet() {
    return flux -> flux.map(name -> "hello " + name);
  }

  @Bean
  public Supplier<Mono<String>> greetSupplier() {
    return () -> Mono.just("hello");
  }
}
