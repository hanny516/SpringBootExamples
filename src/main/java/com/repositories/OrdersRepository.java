package com.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.model.Order;

public interface OrdersRepository extends MongoRepository<Order, Integer> {
	
	public List<Order> findByCreatedBy(String createdBy);

}
