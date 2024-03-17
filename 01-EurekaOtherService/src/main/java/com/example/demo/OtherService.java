package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OtherService {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/")
	public String getInfo()
	{
		String msg = restTemplate.getForObject("http://SimpleService/",String.class);
		return "Unshakable "+msg;
	}
	
}
