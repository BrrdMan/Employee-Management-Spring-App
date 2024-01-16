package com.rtProject.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.rtProject.entities.Employee;

public interface EmployeeService {
	
	List<Employee> getAll();
	void save(Employee emp);
	Employee findById(UUID id);
	void deleteById(UUID id);
	void delete(Employee employee);
	Employee findByEmail(String val);
	Page<Employee> findPaginated(int pageNo, int size, String sortField, String sortDir);
}
