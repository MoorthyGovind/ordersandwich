package com.mrsandwich.service;

import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderRequestDto;
import com.mrsandwich.dto.OrderResponseDto;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;

public interface OrderService {

	public OrderDetailDto getOrderDetailByOrderId(Integer userId, Long orderId)
			throws UserNotFoundException, OrderNotFoundException;

	public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, Integer userId) throws UserNotFoundException;
}
