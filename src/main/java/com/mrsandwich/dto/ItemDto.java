package com.mrsandwich.dto;

import com.mrsandwich.common.OrderSandwichEnum.ItemType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {

	private Integer itemId;
	private String itemName;
	private ItemType itemType;
	private Double price;
}
