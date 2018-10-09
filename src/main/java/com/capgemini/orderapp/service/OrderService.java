package com.capgemini.orderapp.service;

import java.util.List;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.exception.OrderAlreadyRegisteredException;
import com.capgemini.orderapp.exception.OrderDoesnotExistsException;


public interface OrderService {
	public Order addOrder(Order order) throws OrderAlreadyRegisteredException;

	public void deleteOrder(Order order) throws OrderDoesnotExistsException;

	public Order findOrderById(int orderId) throws OrderDoesnotExistsException;

	public List<Order> findOrderByCustomerId(int customerId) throws OrderDoesnotExistsException;


	public List<Order> getAllOrders();

}