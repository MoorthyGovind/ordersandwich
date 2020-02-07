package com.mrsandwich.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrsandwich.common.OrderSandwichEnum;
import com.mrsandwich.constant.AppConstant;
import com.mrsandwich.dto.ItemDto;
import com.mrsandwich.dto.ItemRequestDto;
import com.mrsandwich.dto.OrderDetailDto;
import com.mrsandwich.dto.OrderRequestDto;
import com.mrsandwich.dto.OrderResponseDto;
import com.mrsandwich.entity.Item;
import com.mrsandwich.entity.User;
import com.mrsandwich.entity.UserOrder;
import com.mrsandwich.entity.UserOrderItem;
import com.mrsandwich.exception.ItemNotFoundException;
import com.mrsandwich.exception.OrderNotFoundException;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.respository.ItemRepository;
import com.mrsandwich.respository.UserOrderItemRepository;
import com.mrsandwich.respository.UserOrderRepository;
import com.mrsandwich.respository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderServiceImpl class we are getting the list of orders based on the user ID
 * and get the order details by order ID.
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version V1.1
 *
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserOrderRepository userOrderRepository;

	@Autowired
	UserOrderItemRepository userOrderItemRepository;

	@Autowired
	PaymentRegistry paymentRegistry;

	@Autowired
	ItemRepository itemRepository;

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
	@Override
	public OrderDetailDto getOrderDetailByOrderId(Integer userId, Long orderId)
			throws UserNotFoundException, OrderNotFoundException {
		log.info("Get the user preference items based on userId...");
		// Check the user is present or not based on the userId.
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}

		// Check the user order is present or not based on the orderId.
		Optional<UserOrder> userOrder = userOrderRepository.findById(orderId);
		if (!userOrder.isPresent()) {
			throw new OrderNotFoundException(AppConstant.ORDER_NOT_FOUND);

		}

		List<UserOrderItem> userOrderItems = userOrderItemRepository.findByUserOrderId(userOrder.get());

		List<ItemDto> orderItems = userOrderItems.stream()
				.map(orderItem -> convertItemEntityToDto(orderItem.getItemId())).collect(Collectors.toList());

		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.setOrderItems(orderItems);
		BeanUtils.copyProperties(userOrder.get(), orderDetailDto);
		User userDetail = userOrder.get().getUserId();
		BeanUtils.copyProperties(userDetail, orderDetailDto);

		return orderDetailDto;
	}

	private ItemDto convertItemEntityToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		BeanUtils.copyProperties(item, itemDto);
		return itemDto;
	}

	@Override
	public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, Integer userId) throws UserNotFoundException {
		log.info("Entering into placeOrder() method of EmployeeServiceImpl");
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.error("Exception occured in placeOrder() method: ");
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}

		UserOrder userOrder = new UserOrder();
		BeanUtils.copyProperties(orderRequestDto, userOrder);
		userOrder.setOrderStatus(OrderSandwichEnum.OrderStatus.ORDERED.toString());
		userOrder.setOrderDate(LocalDate.now());
		userOrder.setUserId(user.get());
		userOrderRepository.save(userOrder);

		// Service locator Factory Bean Changes to perform payment
		String message = paymentRegistry.getServiceBean(orderRequestDto.getPaymentType().toString()).payment();

		List<ItemRequestDto> itemDetailList = orderRequestDto.getItemList();
		List<UserOrderItem> userOrderItemList = itemDetailList.stream().map(index -> {
			try {
				return convertToUserOrderItem(index, userOrder);
			} catch (ItemNotFoundException e) {
				log.error("Exception occured in placeOrder() method of EmployeeServiceImpl:" + e.getMessage());
			}
			return null;

		}).collect(Collectors.toList());

		userOrderItemRepository.saveAll(userOrderItemList);

		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setMessage(message);
		orderResponseDto.setOrderId(userOrder.getUserOrderId());
		return orderResponseDto;
	}

	@Override
	public List<OrderDetailDto> getOrdersByUserId(Integer userId) throws UserNotFoundException {
		// Check the user is present or not based on the userId.

		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.error("Exception occured in placeOrder() method: ");
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}
		
		List<OrderDetailDto> userOrderDetails= new ArrayList<>();
		List<UserOrder> userOrders = userOrderRepository.findByUserId(user.get());
		userOrders.forEach(userOrder -> {
			OrderDetailDto orderDetailDto = new OrderDetailDto();
			BeanUtils.copyProperties(userOrder, orderDetailDto);
			List<UserOrderItem> items = userOrderItemRepository.findByUserOrderId(userOrder);
			List<ItemDto> itemDtos = items.stream().map(userOrderItem -> convertItemEntityToDto(userOrderItem.getItemId()))
					.collect(Collectors.toList());
			
			orderDetailDto.setOrderItems(itemDtos);
			userOrderDetails.add(orderDetailDto);
		});
		return userOrderDetails;

	}

	/**
	 * This method converts the list obtained from UI to the UserOrderItem Object
	 * mapping by entities
	 * 
	 * @param itemDetail
	 * @param userOrder
	 * @return
	 * @throws ItemNotFoundException
	 */
	private UserOrderItem convertToUserOrderItem(ItemRequestDto itemDetail, UserOrder userOrder)
			throws ItemNotFoundException {

		Optional<Item> item = itemRepository.findById(itemDetail.getItemId());
		if (!item.isPresent()) {
			log.error("Exception occured in convertToUserOrderItem() method...");
			throw new ItemNotFoundException(AppConstant.ITEM_NOT_FOUND);
		}

		UserOrderItem userOrderItem = new UserOrderItem();

		userOrderItem.setItemId(item.get());
		userOrderItem.setQuantity(itemDetail.getQuantity());
		userOrderItem.setUserOrderId(userOrder);

		return userOrderItem;
	}

}
