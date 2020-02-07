package com.mrsandwich.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemRequestDto {

	private Integer itemId;
	private Integer quantity;
	private Double price;
}
