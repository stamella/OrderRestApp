package com.capgemini.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.orderapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	

}
