package com.hdfc.capstone.employee.service;

import com.hdfc.capstone.employee.vo.EmployeeVO;

public interface IEmployeeService {

	public EmployeeVO getByEmployeeId(int employeeID) throws Exception;
}
