package com.mrsandwich.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemCategoryResponseDto extends ResponseDto{

	private List<ItemCategoryDto> itemCategoryList;
}
