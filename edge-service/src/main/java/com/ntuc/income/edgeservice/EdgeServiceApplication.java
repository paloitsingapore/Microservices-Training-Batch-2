package com.ntuc.income.edgeservice;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}

	@Bean
	GenericFallbackProvider userServiceFallBack(){
		return new GenericFallbackProvider();
	}

	@Bean //<1>
	RateLimiter rateLimiter() {
		return RateLimiter.create(1.0D 	/ 10.0D);
	}

	@Bean
	public RateLimitingFilter rateLimitingFilter(){
		return new RateLimitingFilter(rateLimiter());
	}

}
