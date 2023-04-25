package com.hdfc.capstone.employee.service;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.capstone.employee.entity.Employee;
import com.hdfc.capstone.employee.exception.InvalidEmployeeException;
import com.hdfc.capstone.employee.repository.IEmployeeRepository;
import com.hdfc.capstone.employee.vo.EmployeeVO;

@Service
public class EmployeeServiceImp implements IEmployeeService {
	

	@Autowired
	IEmployeeRepository employeeRepo;
	
	private static final String SECRET_KEY = "SecretKey";
	private static final String SALTVALUE = "pratiksha" ;
	@Override
	public EmployeeVO getByEmployeeId(int employeeID) throws Exception {
		Employee employee = employeeRepo.findById(employeeID).orElse(null);
		if(employee != null) {
			
			String encrypted=EmployeeServiceImp.encrypt(employee.getDateOfBirth().toString());
			System.out.println("Encrypted value = "+encrypted);
			
			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmployeeID(employee.getEmployeeID());
			employeeVO.setEmployeeName(employee.getEmployeeName());
			employeeVO.setDateOfBirth(encrypted);
			
			return employeeVO;
		}
		else {
			throw new InvalidEmployeeException("Invalid EmployeeID");
		}
	}
	
	public static String encrypt(String strToEncrypt) throws Exception {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		/* Returns encrypted value. */
		return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));

	}

}
