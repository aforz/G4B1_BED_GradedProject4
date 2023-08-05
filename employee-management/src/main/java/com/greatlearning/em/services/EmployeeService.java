package com.greatlearning.em.services;

import java.util.List;

import com.greatlearning.em.entities.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int id);

	public void addEmployee(Employee employee);

	public String deleteById(int id);

	public Employee updateEmployee(Employee employee);

	public List<Employee> fetchEmployeeByFirstName(String name);

	List<Employee> fetchAllEmployees(String order);
}
