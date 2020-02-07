package com.mrsandwich.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mrsandwich.common.OrderSandwichEnum.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

	@NotNull(message="Mode of payment cannot be null")
	private PaymentType paymentType;
	
	@NotEmpty(message="itemList cannot be empty")
	List<ItemRequestDto> itemList;
	
	@NotNull(message="totalAmount cannot be null")
	private Double totalAmount;
}
