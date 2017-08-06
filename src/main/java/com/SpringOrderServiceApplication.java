package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.model.Item;
import com.model.Order;
import com.repositories.OrdersRepository;

@SpringBootApplication
public class SpringOrderServiceApplication implements CommandLineRunner {
	
	@Autowired
	private OrdersRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringOrderServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		List<Item> items = new ArrayList<>();
		
		items.add(new Item("item1", 10));
		items.add(new Item("item2", 20));

		repository.save(new Order(5, items, "NEW", "chakri", new Date()));
		repository.save(new Order(6, items, "NEW", "hanny", new Date()));
		
		System.out.println("Fetching all orders details:");
		for (Order order : repository.findAll()) {
			System.out.println(order);
		}
		
		System.out.println("Completed...");
		
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByCreatedBy("9chakri@gmail.com"));

	}
}
