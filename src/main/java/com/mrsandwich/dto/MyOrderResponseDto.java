package com.mrsandwich.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyOrderResponseDto extends ResponseDto{

	private List<OrderDetailDto> orderDetails;
}
