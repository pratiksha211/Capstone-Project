package com.hdfc.capstone.client.employee.controller;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hdfc.capstone.client.employee.entity.Employee;

@RestController
@RequestMapping("/client/employee")
public class ClientController {
	
	@Autowired
	RestTemplate restTemplate;
	String baseUrl = "https://localhost:8485/employee/get/byempId/" ;
	
	private static final String SECRET_KEY = "SecretKey";
	private static final String SALTVALUE = "pratiksha" ;
	
	@GetMapping("/get/empid/{employeeID}")
	public Employee getByEmpID(@PathVariable int employeeID)throws Exception{
		
		Employee employee = restTemplate.getForObject(baseUrl + employeeID , Employee.class);
		String decrypted = ClientController.decrypt(employee.getDateOfBirth());
		System.out.print(decrypted);
		employee.setDateOfBirth(decrypted);
		return employee;
		
	}
	
	public static String decrypt(String strToDecrypt) throws Exception {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	}

}
