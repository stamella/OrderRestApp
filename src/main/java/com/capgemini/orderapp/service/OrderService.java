package com.capgemini.orderapp.service;

import java.util.List;

import com.capgemini.orderapp.Exception.OrderAlreadyRegisteredException;
import com.capgemini.orderapp.Exception.OrderNotFoundException;
import com.capgemini.orderapp.entity.Order;


public interface OrderService {
	
	public Order addOrder(Order order) throws OrderAlreadyRegisteredException;

	public List<Order> getAllOrders();
	
	public Order findOrderById(int orderId) throws OrderNotFoundException;

	public void deleteOrder(Order order) throws OrderNotFoundException;

	public Order editOrder(Order order) throws OrderNotFoundException;

}
