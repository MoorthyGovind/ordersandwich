package com.mrsandwich.service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrsandwich.common.OrderSandwichEnum;
import com.mrsandwich.common.OrderSandwichEnum.ItemType;
import com.mrsandwich.dto.ItemCategoryDto;
import com.mrsandwich.dto.ItemDto;
import com.mrsandwich.entity.Item;
import com.mrsandwich.respository.ItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<ItemCategoryDto> getAllItems() {
		log.info("Starting getAllItems ,inside ItemServiceImpl");
		List<Item> listOfItems = itemRepository.findAll();

		List<ItemCategoryDto> categoryList = new ArrayList<>();
		List<ItemType> categories = new ArrayList<>(EnumSet.allOf(OrderSandwichEnum.ItemType.class));
		categories.forEach(category -> {
			ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
			itemCategoryDto.setCategoryName(category);
			
			List<ItemDto> itemDtos = listOfItems.stream().filter(item -> item.getItemType().equals(category))
					.map(this::convertEntityToDto).collect(Collectors.toList());
			itemCategoryDto.setItemList(itemDtos);
			categoryList.add(itemCategoryDto);
		});

		return categoryList;

	}

	private ItemDto convertEntityToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(item.getItemId());
		itemDto.setItemName(item.getItemName());
		itemDto.setPrice(item.getPrice());
		return itemDto;
	}
}
