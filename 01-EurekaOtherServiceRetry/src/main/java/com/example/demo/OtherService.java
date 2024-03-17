package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class OtherService {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/")
	@Retry(name = "simpleService", fallbackMethod = "getDefaultInfo")
	public String getInfo()
	{
		String msg = restTemplate.getForObject("http://SimpleService/",String.class);
		return "3Tier architecture... "+msg;
	}
	
	public String getDefaultInfo(Exception e)
	{
		return "Prayer has comeback power...Fallback message";
	}
}
