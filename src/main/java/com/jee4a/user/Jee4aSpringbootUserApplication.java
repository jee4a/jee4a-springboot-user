package com.jee4a.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan("com.jee4a")
@SpringBootApplication
public class Jee4aSpringbootUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Jee4aSpringbootUserApplication.class, args);
	}
}
