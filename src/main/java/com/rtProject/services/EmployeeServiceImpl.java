package com.rtProject.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rtProject.dao.EmployeeRepository;
import com.rtProject.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAll() {
		return empRepo.findAll();
	}
	
	@Override
	public Employee findById(UUID id) {
		Optional<Employee> opt = empRepo.findById(id);	
		Employee emp = null;
		if(opt.isPresent())
			emp = opt.get();
		else
			throw new RuntimeException("Employee not found for id: "+id);
		return emp;
	}

	@Override
	public void save(Employee emp) {
		this.empRepo.save(emp);		
	}

	@Override
	public void deleteById(UUID id) {
		empRepo.deleteById(id);
		
	}

	@Override
	public void delete(Employee employee) {
		empRepo.delete(employee);		
	}

	@Override
	public Employee findByEmail(String val) {
		return empRepo.findByEmail(val);
	}
	
	@Override
	public Page<Employee> findPaginated(int pageNo, int size, String sortField, String sortDir){
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
					Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, size, sort);
		
		return this.empRepo.findAll(pageable);
	}

}
