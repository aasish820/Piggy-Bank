package org.piggybank.credidcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PiggyBankServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiggyBankServiceRegistryApplication.class, args);
	}

}
