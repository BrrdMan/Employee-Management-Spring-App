package com.rtProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import com.rtProject.dao.EmployeeRepository;
import com.rtProject.entities.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@SpringBootTest
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
		   @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")})
class RealTimeProject1ApplicationTests {
	
	@Autowired
	EmployeeRepository empRepo;

	@Test
	void ifNewEmployeeSaved_thenSucces() {
		Employee employee = new Employee("name", "test@gmail.com", "MALE", LocalDate.parse("1997-01-01"), "Test");
		empRepo.save(employee);
		
		assertEquals(10, empRepo.findAll().size());
	}

}
