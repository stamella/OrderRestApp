package com.capgemini.orderapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.orderapp.controller.OrderController;
import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {

	private MockMvc mockMvc;

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderController orderController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testAddOrder() throws Exception {
		Order order = new Order(221700, 12345, 12345, LocalDate.of(2015, 05, 03));
		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(order);
		String content = "{\r\n" + "  \"orderId\": 221700,\r\n" + "  \"products\": 12345,\r\n"
				+ "  \"customerId\": 12345,\r\n" + "  \"orderDate\": \"2015-05-03\"\r\n" + "}";
		mockMvc.perform(post("/v1/order").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$.orderId").value(221700));
	}

	@Test
	public void testGetOrder() throws Exception {
		Order order = new Order(221700, 12345, 12345, LocalDate.of(2015, 05, 03));
		when(orderService.findOrderById(221700)).thenReturn(order);

		mockMvc.perform(get("/v1/order/221700").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(jsonPath("$.orderId").value(221700));
	}

	@Test
	public void testDeleteOrder() throws Exception {
		Order order = new Order(221700, 12345, 12345, LocalDate.of(2015, 05, 03));
		when(orderService.findOrderById(221700)).thenReturn(order);

		mockMvc.perform(delete("/v1/order/221700").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk());

		verify(orderService, times(1)).deleteOrder(order);
	}

}