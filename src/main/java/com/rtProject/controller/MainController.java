package com.rtProject.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtProject.entities.Employee;
import com.rtProject.entities.Gender;
import com.rtProject.services.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/home")
public class MainController {
	
	@Autowired
	EmployeeService serv;
	
	@GetMapping("/new")
	public String homePage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("gender_value", Gender.values());
		return "employee";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid Employee employee, BindingResult br, Model model) {
		if(br.hasErrors()) {
			model.addAttribute("employee", employee);
			model.addAttribute("gender_value", Gender.values());
			return "employee";
		}
		else {
			serv.save(employee);
			
			return "redirect:/home";
		}
		
	}
	
	@GetMapping
	public String viewEmployees(Model model) {
		return findPaginated(1, "name", "asc", model);
	}
	
	@GetMapping("/update")
	public String updateEmployee(@RequestParam("id") UUID empId, Model model) {
		Employee employee = serv.findById(empId);
		model.addAttribute("employee", employee);
		model.addAttribute("gender_value", Gender.values());
		return "employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") UUID empId, Model model) {
		Employee employee = serv.findById(empId);
		serv.delete(employee);
		return "redirect:/home";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir, Model model) {
		int size = 10;
		
		Page<Employee> page = serv.findPaginated(pageNo, size, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc":"asc");
		
		model.addAttribute("listEmployees", listEmployees);
		
		return "view";
	}

}
