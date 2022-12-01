package com.ty.foodorderapp.foodorder_app.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.foodorderapp.foodorder_app.dto.FoodOrder;
import com.ty.foodorderapp.foodorder_app.exception.NoSuchIdFound;
import com.ty.foodorderapp.foodorder_app.exception.UnableToUpdateUser;
import com.ty.foodorderapp.foodorder_app.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(NoSuchIdFound.class)
	public ResponseEntity<ResponseStructure<String>> noSuchIdFound(NoSuchIdFound found){
		ResponseEntity<ResponseStructure<String>> responseEntity ;
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessege("No Id Found");
		responseStructure.setData(found.getMessage());
		return responseEntity =new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnableToUpdateUser.class)
	public ResponseEntity<ResponseStructure<String>> unableToUpdate(UnableToUpdateUser updateUser){
		ResponseEntity<ResponseStructure<String>> responseEntity ;
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessege("No Id Found Unable To Update");
		responseStructure.setData(updateUser.getMessage());
		return responseEntity =new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
