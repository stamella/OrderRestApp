package com.capgemini.orderapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.orderapp.Exception.OrderAlreadyRegisteredException;
import com.capgemini.orderapp.Exception.OrderNotFoundException;
import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) throws OrderAlreadyRegisteredException  {
		
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (!orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderAlreadyRegisteredException(
				"Registration failed!! Order Already registered with id" + order.getOrderId());
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Order order) throws OrderNotFoundException {
	   
		Optional<Order> Optional = orderRepository.findById(order.getOrderId());
		if (Optional.isPresent()) {
			orderRepository.delete(order);
			return;
		}
		throw new OrderNotFoundException("Delete failed!! Order not found with id " + order.getOrderId());
		
	}

	@Override
	public Order editOrder(Order order) throws OrderNotFoundException {
		
		Optional<Order> customerFromDb = orderRepository.findById(order.getOrderId());
		if (customerFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderNotFoundException("Edit failed!! No order found with id" + order.getOrderId());
	}

	@Override
	public Order findOrderById(int orderId) throws OrderNotFoundException {
		
		Optional<Order> customerFromDb = orderRepository.findById(orderId);
		if (customerFromDb.isPresent()) {
			return customerFromDb.get();
		}
		throw new OrderNotFoundException("Search failed!! Order not found with id " + orderId);
	}

}
