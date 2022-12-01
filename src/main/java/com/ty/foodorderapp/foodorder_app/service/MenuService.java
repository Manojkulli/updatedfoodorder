package com.ty.foodorderapp.foodorder_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodorderapp.foodorder_app.dao.MenuDao;
import com.ty.foodorderapp.foodorder_app.dto.Menu;
import com.ty.foodorderapp.foodorder_app.dto.User;
import com.ty.foodorderapp.foodorder_app.exception.NoSuchIdFound;
import com.ty.foodorderapp.foodorder_app.exception.UnableToUpdateUser;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu) {
		ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
		ResponseEntity<ResponseStructure<Menu>> responseEntity;
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessege("Menu saved");
		responseStructure.setData(menuDao.saveMenu(menu));
		return responseEntity = new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
		ResponseEntity<ResponseStructure<Menu>> responseEntity;
		Menu menu = menuDao.getMenuById(id);
		if (menu != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Found");
			responseStructure.setData(menu);
			return responseEntity = new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoSuchIdFound();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteMenuById(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessege("Deleted");
		responseStructure.setData(menuDao.deleteMenuById(id));
		return responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id) {
		Menu menu2 = menuDao.getMenuById(id);
		ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
		ResponseEntity<ResponseStructure<Menu>> responseEntity;
		if (menu2 != null) {
			menu.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Updated");
			responseStructure.setData(menuDao.updateMenu(menu));
			return responseEntity = new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnableToUpdateUser();
		}
	}
}
