package com.ntuc.income.userservice;

import com.ntuc.income.userservice.entity.User;
import com.ntuc.income.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class BootifulHelloApplication implements ApplicationRunner {

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


}
