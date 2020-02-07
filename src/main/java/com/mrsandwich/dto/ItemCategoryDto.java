package com.mrsandwich.dto;

import java.util.List;

import com.mrsandwich.common.OrderSandwichEnum.ItemType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemCategoryDto {

	private ItemType categoryName;
	private List<ItemDto> itemList;
}
