package com.capgemini.orderapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.orderapp.Exception.OrderAlreadyRegisteredException;
import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.service.OrderService;

public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) throws OrderAlreadyRegisteredException {
		logger.info("Created");
		return new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.CREATED);
	}


	@PutMapping("/order")
	public ResponseEntity<Order> editOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.editOrder(order), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int customerId) {
		return new ResponseEntity<Order>(orderService.findOrderById(customerId), HttpStatus.OK);
	}

	@DeleteMapping("/order/orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int customerId) {
		Order customer = orderService.findOrderById(customerId);
		orderService.deleteOrder(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public @ResponseBody ResponseEntity<List<Order>> getAllOrder() {
		return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
	}



}
