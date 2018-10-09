package com.capgemini.orderapp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.exception.OrderAlreadyRegisteredException;
import com.capgemini.orderapp.exception.OrderDoesnotExistsException;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) throws OrderAlreadyRegisteredException {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (!orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderAlreadyRegisteredException("Order id " + order.getOrderId() + " is already done.");

	}

	@Override
	public void deleteOrder(Order order) throws OrderDoesnotExistsException {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (orderFromDb.isPresent()) {
			orderRepository.delete(order);
			return;
		}
		throw new OrderDoesnotExistsException("Order id " + order.getOrderId() + " does not exists for deletion");
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order findOrderById(int orderId) throws OrderDoesnotExistsException {
		Optional<Order> orderFromDb = orderRepository.findById(orderId);
		if (orderFromDb.isPresent()) {
			return orderFromDb.get();
		}
		throw new OrderDoesnotExistsException("Order id " + orderId + " does not exists to search");
	}

	@Override
	public List<Order> findOrderByCustomerId(int customerId) throws OrderDoesnotExistsException {
		return orderRepository.findByCustomerId(customerId);

	}

}