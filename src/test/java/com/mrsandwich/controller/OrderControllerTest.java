package com.mrsandwich.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.mrsandwich.common.OrderSandwichEnum;
import com.mrsandwich.dto.MyOrderResponseDto;
import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderDetailResponseDto;
import com.mrsandwich.dto.OrderRequestDto;
import com.mrsandwich.dto.OrderResponseDto;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@InjectMocks
	OrderController orderController;

	@Mock
	OrderService orderService;

	OrderRequestDto orderRequestDto = new OrderRequestDto();
	OrderDetailDto orderDetailDto = new OrderDetailDto();
	OrderResponseDto orderResponseDto = new OrderResponseDto();

	@Before
	public void init() {
		orderRequestDto.setTotalAmount(150.00);

		orderDetailDto.setPaymentType(OrderSandwichEnum.PaymentType.PAYTM);

		orderResponseDto.setOrderId(101L);
	}

	@Test
	public void testGetOrderDetailByOrderId() throws UserNotFoundException, OrderNotFoundException {
		// given
		when(orderService.getOrderDetailByOrderId(1, 101L)).thenReturn(orderDetailDto);
		// when
		ResponseEntity<OrderDetailResponseDto> response = orderController.getOrderDetailByOrderId(1, 101L);
		// then
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testPlaceOrder() throws UserNotFoundException {
		// given
		when(orderService.placeOrder(orderRequestDto, 1)).thenReturn(orderResponseDto);
		// when
		ResponseEntity<OrderResponseDto> response = orderController.placeOrder(orderRequestDto, 1);
		// then
		assertEquals(200, response.getBody().getStatusCode());
	}
	
	@Test
	public void testGetOrdersByUserId() throws UserNotFoundException {
		List<OrderDetailDto> details = new ArrayList<>();
		details.add(orderDetailDto);
		// given

		when(orderService.getOrdersByUserId(1)).thenReturn(details);
		// when
		ResponseEntity<MyOrderResponseDto> response = orderController.getOrdersByUserId(1);
		// then
		assertEquals(200, response.getBody().getStatusCode());
	}

}
