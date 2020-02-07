package com.mrsandwich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrsandwich.dto.ItemCategoryDto;
import com.mrsandwich.service.ItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Amala
 *
 */

@RestController
@RequestMapping("/items")
@Slf4j
@CrossOrigin
public class ItemController {
	@Autowired
	ItemService itemService;
   @GetMapping
	ResponseEntity<List<ItemCategoryDto>> getAllItems() {
		log.info("Starting getAllItems() method in side ItemController ");
		return ResponseEntity.ok(itemService.getAllItems());
	}

}
