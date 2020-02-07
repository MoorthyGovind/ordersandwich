package com.mrsandwich.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPreferenceResponseDto extends ResponseDto{

	private Set<ItemDto> itemList;
}
