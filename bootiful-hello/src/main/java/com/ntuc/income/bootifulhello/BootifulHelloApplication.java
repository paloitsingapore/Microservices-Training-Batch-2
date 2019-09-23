package com.ntuc.income.bootifulhello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("")
public class BootifulHelloApplication {

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
}
