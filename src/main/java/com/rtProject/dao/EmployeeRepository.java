package com.rtProject.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rtProject.entities.Employee;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	Employee findByEmail(String val);

}
