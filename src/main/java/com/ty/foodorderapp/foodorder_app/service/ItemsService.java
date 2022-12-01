package com.ty.foodorderapp.foodorder_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodorderapp.foodorder_app.dao.ItemsDao;
import com.ty.foodorderapp.foodorder_app.dto.Items;
import com.ty.foodorderapp.foodorder_app.dto.Menu;
import com.ty.foodorderapp.foodorder_app.exception.NoSuchIdFound;
import com.ty.foodorderapp.foodorder_app.exception.UnableToUpdateUser;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@Service
public class ItemsService {

	@Autowired
	ItemsDao itemsDao;

	public ResponseEntity<ResponseStructure<Items>> updateItems(Items items, int id) {
		Items items2 = itemsDao.getItemById(id);
		ResponseStructure<Items> responseStructure = new ResponseStructure<Items>();
		ResponseEntity<ResponseStructure<Items>> responseEntity;
		if (items != null) {
			items.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("updated");
			responseStructure.setData(itemsDao.updateItem(items));
			return responseEntity = new ResponseEntity<ResponseStructure<Items>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnableToUpdateUser();
		}
	}

	public ResponseEntity<ResponseStructure<Items>> getItemsById(int id) {
		Items items2 = itemsDao.getItemById(id);
		ResponseStructure<Items> responseStructure = new ResponseStructure<Items>();
		ResponseEntity<ResponseStructure<Items>> responseEntity;
		if (items2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Found");
			responseStructure.setData(items2);
			return responseEntity = new ResponseEntity<ResponseStructure<Items>>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoSuchIdFound();
		}
	}

}
