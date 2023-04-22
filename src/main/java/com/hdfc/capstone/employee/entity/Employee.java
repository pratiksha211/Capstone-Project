package com.hdfc.capstone.employee.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@Column(name ="employee_id")
	private int employeeID;
	
	private String employeeName;
	
	@Column(name="DateOfBirth")
	private Date dateOfBirth;

	

}
