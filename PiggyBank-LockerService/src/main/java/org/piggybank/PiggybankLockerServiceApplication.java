package org.piggybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PiggybankLockerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiggybankLockerServiceApplication.class, args);
	}

}
