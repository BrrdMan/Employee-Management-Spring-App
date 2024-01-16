package com.rtProject.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.rtProject.validators.UniqueValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="emp_id")
	private UUID empId;
	
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=50, message="Name must be between 2 and 50 characters")
	@Column(name="name")
	private String name;
	
	@Email(message="Please enter valid email")
	@NotBlank(message="Please enter your email")
	@UniqueValue
	@Column(name="email")
	private String email;
	
	@NotBlank(message="Please select your gender")
	@Column(name="gender")
	private String gender;
	
	@NotNull(message="Please enter your date of birth")
	@Past(message="Enter valid date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="dob")
	private LocalDate dob;
	
	@NotBlank(message="Please select your address")
	@Column(name="address")
	private String address;
	
	public Employee() {
		
	}
	
	public Employee(String name, String email, String gender, LocalDate dob, String address) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
	}

	public UUID getEmpId() {
		return empId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public void setEmpId(UUID empId) {
		this.empId = empId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
