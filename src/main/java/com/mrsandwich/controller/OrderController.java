package com.mrsandwich.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrsandwich.constant.AppConstant;
import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderDetailResponseDto;
import com.mrsandwich.dto.OrderRequestDto;
import com.mrsandwich.dto.OrderResponseDto;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.service.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderController class we are getting the list of orders based on the user ID
 * and get the order details by order ID.
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version V1.1
 *
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Slf4j
public class OrderController {

	@Autowired
	OrderService orderService;

	/**
	 * Get the order detail based on by orderId
	 * 
	 * @param userId  - ID of the login user
	 * @param orderId - ID of the order detail
	 * @return responseDto - details of the order detail along status code and
	 *         message.
	 * @throws UserNotFoundException  - if user is not present based on giving
	 *                                userId that time we are throws this exception
	 * @throws OrderNotFoundException - if order is not present based on giving
	 *                                orderId that time we are throws this exception
	 */
	@GetMapping("/{userId}/orders/{orderId}")
	public ResponseEntity<OrderDetailResponseDto> getOrderDetailByOrderId(@PathVariable Integer userId,
			@PathVariable Long orderId) throws UserNotFoundException, OrderNotFoundException {
		OrderDetailResponseDto responseDto = new OrderDetailResponseDto();
		OrderDetailDto orderDetailDto = orderService.getOrderDetailByOrderId(userId, orderId);
		responseDto.setOrderDetail(orderDetailDto);
		responseDto.setStatusCode(HttpStatus.OK.value());
		responseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	/**
	 * This method is used to place a new order from the existing authorized vendor
	 * stall with their available food menu
	 * 
	 * @author Govindasamy.C
	 * @param orderRequestDto - Takes parameters like Item Details,Payment Opted
	 * @param userId          - takes of type Long which is the userId
	 * @return OrderResponseDto - returns places order Id along with the status
	 *         codes
	 * @throws UserNotFoundException
	 * @since 07-February-2020
	 */
	@PostMapping("/{userId}/orders")
	public ResponseEntity<OrderResponseDto> placeOrder(@Valid @RequestBody OrderRequestDto orderRequestDto,
			@PathVariable Integer userId) throws UserNotFoundException {
		log.info("Entering into placeOrder() method of OrderController");
		OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto, userId);
		orderResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
	}
}
