package com.mrsandwich.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrsandwich.constant.AppConstant;
import com.mrsandwich.dto.ItemDto;
import com.mrsandwich.dto.LoginRequestDto;
import com.mrsandwich.dto.LoginResponseDto;
import com.mrsandwich.entity.Item;
import com.mrsandwich.entity.User;
import com.mrsandwich.entity.UserOrder;
import com.mrsandwich.entity.UserOrderItem;
import com.mrsandwich.exception.UserNotFoundException;
import com.mrsandwich.respository.UserOrderItemRepository;
import com.mrsandwich.respository.UserOrderRepository;
import com.mrsandwich.respository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserOrderRepository userOrderRepository;

	@Autowired
	UserOrderItemRepository userOrderItemRepository;

	@Override
	public Set<ItemDto> getAllUserPreferenceItems(Integer userId) throws UserNotFoundException {
		log.info("Get the user preference items based on userId...");
		// Check the user is present or not based on the userId.
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}

		Set<ItemDto> itemDtos = new HashSet<>();
		List<UserOrder> userOrders = userOrderRepository.findTop3ByUserId(user.get());
		userOrders.forEach(userOrder -> {
			List<UserOrderItem> listOrderItems = userOrderItemRepository.findByUserOrderId(userOrder);

			Set<Item> items = listOrderItems.stream().map(orderItem -> orderItem.getItemId())
					.collect(Collectors.toSet());

			items.forEach(item -> {
				ItemDto itemDto = convertItemEntityToDto(item);
				itemDtos.add(itemDto);
			});

		});

		return itemDtos;
	}

	@Override
	public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) throws UserNotFoundException {

		Optional<User> user = userRepository.findByPhoneNumberAndPassword(loginRequestDto.getPhoneNumber(),
				loginRequestDto.getPassword());

		LoginResponseDto loginResponseDto = new LoginResponseDto();

		if (user.isPresent()) {
			BeanUtils.copyProperties(user.get(), loginResponseDto);
			loginResponseDto.setName(user.get().getName());

			loginResponseDto.setUserId(user.get().getUserId());
			loginResponseDto.setRole(user.get().getRole().toString());
			loginResponseDto.setMessage(AppConstant.LOGIN_SCCUESS_MESSAGE);

			loginResponseDto.setStatusCode(AppConstant.SUCCESS_STATUS_CODE);

			log.info("UserServiceImpl authenticateUser ---> user signed in");
			return loginResponseDto;
		} else {
			log.error("UserServiceImpl authenticateUser ---> NotFoundException occured");
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}

	}

	private ItemDto convertItemEntityToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		BeanUtils.copyProperties(item, itemDto);
		return itemDto;
	}

}
