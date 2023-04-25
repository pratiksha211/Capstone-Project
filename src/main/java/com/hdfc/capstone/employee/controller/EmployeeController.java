package com.hdfc.capstone.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.capstone.employee.exception.InvalidEmployeeException;
import com.hdfc.capstone.employee.service.IEmployeeService;
import com.hdfc.capstone.employee.vo.EmployeeVO;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping("/get/byempId/{employeeID}")
	public EmployeeVO getByEmployeeId(@PathVariable int employeeID)throws Exception{
		logger.info("Record of EmployeeID("+employeeID+")is called");
		return employeeService.getByEmployeeId(employeeID);
	}
	

}
