package com.mrsandwich.dto;

import java.time.LocalDate;
import java.util.List;

import com.mrsandwich.common.OrderSandwichEnum.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailDto {

	private LocalDate orderDate;
	private Double totalAmount;
	private PaymentType paymentType;
	private String address1;
	private String address2;
	private String place;
	private Long pinCode;
	private List<ItemDto> orderItems;
}
