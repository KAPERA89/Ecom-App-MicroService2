package com.sid.orderservice;

import com.sid.orderservice.entities.Order;
import com.sid.orderservice.entities.ProductItem;
import com.sid.orderservice.enums.OrderStatus;
import com.sid.orderservice.model.Customer;
import com.sid.orderservice.model.Product;
import com.sid.orderservice.repositories.OrderRepository;
import com.sid.orderservice.repositories.ProductItemRepository;
import com.sid.orderservice.services.CustomerRestClientService;
import com.sid.orderservice.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(OrderRepository orderRepository, CustomerRestClientService customerRestClientService, ProductItemRepository productItemRepository, InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers = customerRestClientService.allCustomers().getContent().stream().toList();
			List<Product> products = inventoryRestClientService.allProducts().getContent().stream().toList();

			Long customerId = 1L;
			Customer customer = customerRestClientService.customerById(customerId);
			for(int i =0; i<20; i++){
				 Order order = Order.builder()
						 .customerId(customers.get(new Random().nextInt(customers.size())).getId())
						 .orderStatus(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
						 .createdAt(new Date())
						 .build();

				 Order savedOrder = orderRepository.save(order);

				 for(int j = 0; j< products.size(); j++){
					 if(Math.random()>0.70) {
						 ProductItem productItem = ProductItem.builder()
								 .order(savedOrder)
								 .productId(products.get(j).getId())
								 .price(products.get(j).getPrice())
								 .price(1 + new Random().nextInt(10))
								 .discount(Math.random())
								 .build();

						 productItemRepository.save(productItem);
					 }
				 }
			}
		};
	}
}
