package com.ty.foodorderapp.foodorder_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.foodorderapp.foodorder_app.dao.UserDao;
import com.ty.foodorderapp.foodorder_app.dto.FoodOrder;
import com.ty.foodorderapp.foodorder_app.dto.User;
import com.ty.foodorderapp.foodorder_app.exception.NoSuchIdFound;
import com.ty.foodorderapp.foodorder_app.exception.UnableToUpdateUser;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		ResponseEntity<ResponseStructure<User>> responseEntity;
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessege("saved");
		responseStructure.setData(userDao.saveUser(user));
		return responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int id) {

		User user2 = userDao.getUserById(id);
		ResponseEntity<ResponseStructure<User>> responseEntity;
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if (user2 != null) {
			user.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessege("Found");
			responseStructure.setData(userDao.saveUser(user));
			return responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		} else {
			throw new UnableToUpdateUser();
		}
		
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		ResponseEntity<ResponseStructure<User>> responseEntity;
		responseStructure.setStatus(HttpStatus.OK.value());
		User user = userDao.getUserById(id);
		if (user != null) {
			responseStructure.setMessege("Found");
			responseStructure.setData(user);
			return responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		} else {
			throw new NoSuchIdFound();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		ResponseEntity<ResponseStructure<String>> responseEntity;
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessege("deleted");
		responseStructure.setData(userDao.deleteById(id));
		return responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);

	}

}
