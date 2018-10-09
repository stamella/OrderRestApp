package com.capgemini.orderapp.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.orderapp.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

	@Query("{ 'customerId' : ?0}")
	public List<Order> findByCustomerId(int customerId);
	
	
	
}
	
	


