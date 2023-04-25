package com.hdfc.capstone.client.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	private int employeeID;
	private String employeeName;
	private String dateOfBirth;

}
