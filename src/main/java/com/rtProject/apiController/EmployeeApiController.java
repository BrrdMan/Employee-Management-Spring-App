package com.rtProject.apiController;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rtProject.entities.Employee;
import com.rtProject.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api-employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeService serv;
	
	@GetMapping
	public List<Employee> getAll(){
		return serv.getAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") UUID id) {
		return serv.findById(id);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void addEmployee(@Valid @RequestBody Employee emp) {
		serv.save(emp);
	}
	
	@PutMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public void modifyEmployee(@Valid @RequestBody Employee emp) {
		serv.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id") UUID id) {
		try {
			serv.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}

}
