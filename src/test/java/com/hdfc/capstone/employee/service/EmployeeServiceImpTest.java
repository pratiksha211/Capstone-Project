package com.hdfc.capstone.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.capstone.employee.exception.InvalidEmployeeException;
import com.hdfc.capstone.employee.vo.EmployeeVO;

@SpringBootTest
class EmployeeServiceImpTest {
	
	@Autowired
	IEmployeeService service;


	@Test
	void testGetByEmployeeId() throws Exception {

		EmployeeVO employee = service.getByEmployeeId(1101);
		assertEquals("Pratiksha Patil", employee.getEmployeeName());
		
	}
	
	@Test
	public void testGetInvalidEmployeeById() throws InvalidEmployeeException {

		assertThrows(InvalidEmployeeException.class, () -> service.getByEmployeeId(1115));
	}

}
