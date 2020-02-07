package com.mrsandwich.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.mrsandwich.common.OrderSandwichEnum;
import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderDetailResponseDto;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;

	OrderDetailDto orderDetailDto = new OrderDetailDto();
	@Before
	public void init() {
		orderDetailDto.setPaymentType(OrderSandwichEnum.PaymentType.PAYTM);
	}
	
	@Test
	public void testGetOrderDetailByOrderId() throws UserNotFoundException, OrderNotFoundException {
		when(orderService.getOrderDetailByOrderId(1, 101L)).thenReturn(orderDetailDto);
		ResponseEntity<OrderDetailResponseDto> response = orderController.getOrderDetailByOrderId(1, 101L);
		assertEquals(200, response.getBody().getStatusCode());
	}
}
