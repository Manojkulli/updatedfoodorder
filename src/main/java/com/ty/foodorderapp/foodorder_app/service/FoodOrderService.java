package com.ty.foodorderapp.foodorder_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodorderapp.foodorder_app.dao.FoodOrderDao;
import com.ty.foodorderapp.foodorder_app.dto.FoodOrder;
import com.ty.foodorderapp.foodorder_app.dto.Product;
import com.ty.foodorderapp.foodorder_app.exception.NoSuchIdFound;
import com.ty.foodorderapp.foodorder_app.exception.UnableToUpdateUser;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder) {
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity;
		List<Product> list = foodOrder.getProducts();
		double totalcost = 0;
		for (Product product : list) {
			totalcost += product.getPrice() * product.getQuantity();
		}
		totalcost += totalcost * 0.18;
		foodOrder.setTotalcost(totalcost);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessege("saved");
		responseStructure.setData(foodOrderDao.saveFoodOrder(foodOrder));
		return responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id) {
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity;
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		FoodOrder foodOrder = foodOrderDao.getFoodOrderById(id);
		if (foodOrder != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Found");
			responseStructure.setData(foodOrder);
			return responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NoSuchIdFound();
		}

	}

	public ResponseEntity<ResponseStructure<String>> deleteFoodOrderById(int id) {
		ResponseEntity<ResponseStructure<String>> responseEntity;
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessege("Deleted");
		responseStructure.setData(foodOrderDao.deleteFoodOrderById(id));
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder, int id) {
		FoodOrder foodOrder2 = foodOrderDao.getFoodOrderById(id);
		ResponseEntity<ResponseStructure<FoodOrder>> responseEntity;
		ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
		if (foodOrder2 != null) {
			foodOrder.setId(id);
			List<Product> list = foodOrder.getProducts();
			double totalcost = 0;
			for (Product product : list) {
				totalcost += product.getPrice() * product.getQuantity();
			}
			totalcost += totalcost * 0.18;
			foodOrder.setTotalcost(totalcost);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Updated");
			responseStructure.setData(foodOrderDao.updateFoodOrder(foodOrder));
			return responseEntity = new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnableToUpdateUser();
		}
	}
}
