package com.mrsandwich.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailResponseDto {

	private String message;
	private Integer statusCode;
	private OrderDetailDto orderDetail;
}
