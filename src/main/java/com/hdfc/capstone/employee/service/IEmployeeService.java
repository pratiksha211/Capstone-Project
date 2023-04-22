package com.hdfc.capstone.employee.service;

import com.hdfc.capstone.employee.entity.Employee;
import com.hdfc.capstone.employee.exception.InvalidEmployeeException;

public interface IEmployeeService {

	public Employee getByEmployeeId(int employeeID) throws InvalidEmployeeException;
}
