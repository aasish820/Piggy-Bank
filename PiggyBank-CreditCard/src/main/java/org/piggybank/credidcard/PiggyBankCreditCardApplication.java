package org.piggybank.credidcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//test
@EnableDiscoveryClient
@SpringBootApplication
public class PiggyBankCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiggyBankCreditCardApplication.class, args);
	}

}
