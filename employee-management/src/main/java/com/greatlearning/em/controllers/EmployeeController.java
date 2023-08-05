package com.greatlearning.em.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.em.entities.Employee;
import com.greatlearning.em.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> fetchAllEmployee() {
		return employeeService.findAll();
	}

	@PostMapping
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@GetMapping("/{id}")
	public Employee searchEmployee(@PathVariable("id") int id) {
		return employeeService.findById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		return employeeService.deleteById(id);
	}

	@GetMapping("/search/{name}")
	public List<Employee> fetchEmployeeByFirstName(@PathVariable("name") String name) {
		return employeeService.fetchEmployeeByFirstName(name);
	}

	@GetMapping("/sort")
	public List<Employee> sortByRequestedOrder(
			@RequestParam(name = "order", required = false, defaultValue = "asc") String order) {
		return employeeService.fetchAllEmployees(order);
	}
}
