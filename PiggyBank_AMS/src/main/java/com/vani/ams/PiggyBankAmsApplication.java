package com.vani.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PiggyBankAmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiggyBankAmsApplication.class, args);
	}

}
