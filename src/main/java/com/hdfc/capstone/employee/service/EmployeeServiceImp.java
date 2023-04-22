package com.hdfc.capstone.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.capstone.employee.entity.Employee;
import com.hdfc.capstone.employee.exception.InvalidEmployeeException;
import com.hdfc.capstone.employee.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImp implements IEmployeeService {
	

	@Autowired
	IEmployeeRepository employeeRepo;

	@Override
	public Employee getByEmployeeId(int employeeID) throws InvalidEmployeeException {
		Employee employee = employeeRepo.findById(employeeID).orElse(null);
		if(employee != null) {
			return employee;
		}
		else {
			throw new InvalidEmployeeException("Invalid EmployeeID");
		}
	}

}
