package com.sid.customerservice;

import com.sid.customerservice.entities.Customer;
import com.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			//restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(List.of(
					Customer.builder().name("othmane").email("othmane@gmail.com").build(),
					Customer.builder().name("othmane2").email("othmane2@gmail.com").build(),
					Customer.builder().name("othmane3").email("othmane3@gmail.com").build()
			));

			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
