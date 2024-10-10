package com.gblog.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/")
@RestController
public class BlogApplication {

	@GetMapping("/")
	public String healthCheck(){
		return "OK";
	}
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
