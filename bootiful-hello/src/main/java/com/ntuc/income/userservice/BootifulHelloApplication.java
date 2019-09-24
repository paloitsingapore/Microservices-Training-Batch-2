package com.ntuc.income.userservice;

import com.ntuc.income.userservice.entity.User;
import com.ntuc.income.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableBinding(Sink.class)

public class BootifulHelloApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(BootifulHelloApplication.class);
	@Autowired UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BootifulHelloApplication.class, args);
	}

	@GetMapping("/greet/{name}")
	public String greet(@PathVariable String name) {
		return "Hello "+ name +"!";
	}

	@PostMapping("/greetpost")
	public String greetPost(@RequestBody String name){
		return "Hello  "+ name +"! from post ";
	}

	@Override public void run(ApplicationArguments args) throws Exception {
		userRepository.save(new User("John", 22));
		userRepository.save(new User("Doe", 43));
		userRepository.save(new User("Jill", 32));

	}

	@StreamListener(Sink.INPUT)
	public void listen(@Payload String event,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
			@Header(KafkaHeaders.OFFSET) Long offset){
		log.info(event);
	}


}
