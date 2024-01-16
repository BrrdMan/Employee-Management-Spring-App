package com.rtProject.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.rtProject.entities.Employee;
import com.rtProject.services.EmployeeService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{
	
	@Autowired
	EmployeeService serv;
	
	public boolean isValid(String val, ConstraintValidatorContext con) {
		
		Employee emp = serv.findByEmail(val);
		
		if(emp != null)
			return false;
		else
			return true;
	}

}
