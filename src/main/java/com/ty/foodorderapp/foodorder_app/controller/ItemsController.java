package com.ty.foodorderapp.foodorder_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodorderapp.foodorder_app.dto.Items;
import com.ty.foodorderapp.foodorder_app.service.ItemsService;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@RestController
@RequestMapping("item")
public class ItemsController {

	@Autowired
	ItemsService itemsService;
	
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Items>> updateItems(@RequestBody Items items,@RequestParam int id) {
		return itemsService.updateItems(items, id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Items>> getItems(@RequestParam int id) {
		return itemsService.getItemsById(id);
	}
}
