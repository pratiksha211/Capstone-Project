package com.hdfc.capstone.client.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeIDExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception exp){
		return new ResponseEntity<String>(exp.getMessage().toString(),HttpStatus.EXPECTATION_FAILED);
	}

}
