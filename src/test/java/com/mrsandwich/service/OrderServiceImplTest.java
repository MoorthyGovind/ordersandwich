package com.mrsandwich.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mrsandwich.common.OrderSandwichEnum;
import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderRequestDto;
import com.mrsandwich.dto.OrderResponseDto;
import com.mrsandwich.entity.Item;
import com.mrsandwich.entity.User;
import com.mrsandwich.entity.UserOrder;
import com.mrsandwich.entity.UserOrderItem;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.respository.UserOrderItemRepository;
import com.mrsandwich.respository.UserOrderRepository;
import com.mrsandwich.respository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	OrderServiceImpl orderServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserOrderRepository userOrderRepository;

	@Mock
	UserOrderItemRepository userOrderItemRepository;

	@Mock
	PaymentRegistry paymentRegistry;
	
	User user = new User();
	UserOrder userOrder = new UserOrder();
	UserOrderItem userOrderItem = new UserOrderItem();
	List<UserOrderItem> orderItems = new ArrayList<>();

	Item item = new Item();

	OrderRequestDto orderRequestDto = new OrderRequestDto();
	OrderDetailDto orderDetailDto = new OrderDetailDto();
	OrderResponseDto orderResponseDto = new OrderResponseDto();

	@Before
	public void init() {
		user.setUserId(1);

		userOrder.setUserId(user);

		item.setItemId(1);
		userOrderItem.setUserOrderId(userOrder);
		userOrderItem.setItemId(item);
		orderItems.add(userOrderItem);

		orderRequestDto.setTotalAmount(150.00);
		orderRequestDto.setPaymentType(OrderSandwichEnum.PaymentType.PAYTM);
		orderDetailDto.setPaymentType(OrderSandwichEnum.PaymentType.PAYTM);
		orderResponseDto.setOrderId(101L);
	}

	@Test
	public void testGetOrderDetailByOrderId() throws UserNotFoundException, OrderNotFoundException {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		when(userOrderRepository.findById(101L)).thenReturn(Optional.of(userOrder));
		when(userOrderItemRepository.findByUserOrderId(userOrder)).thenReturn(orderItems);
		OrderDetailDto response = orderServiceImpl.getOrderDetailByOrderId(1, 101L);
		assertThat(response.getOrderItems()).hasSize(1);
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetOrderDetailByOrderIdForUserNotEx() throws UserNotFoundException, OrderNotFoundException {
		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		orderServiceImpl.getOrderDetailByOrderId(1, 101L);
	}

	@Test(expected = OrderNotFoundException.class)
	public void testGetOrderDetailByOrderIdForOrderNotEx() throws UserNotFoundException, OrderNotFoundException {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		when(userOrderRepository.findById(101L)).thenReturn(Optional.ofNullable(null));
		orderServiceImpl.getOrderDetailByOrderId(1, 101L);
	}
}
