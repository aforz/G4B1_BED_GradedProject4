package com.greatlearning.em.services.impls;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greatlearning.em.entities.Employee;
import com.greatlearning.em.repositories.EmployeeRepository;
import com.greatlearning.em.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee findById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No employee with id " + id));
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		boolean exist = employeeRepository.existsById(employee.getId());
		if (exist) {
			return employeeRepository.saveAndFlush(employee);
		} else {
			throw new RuntimeException("No employee with id " + employee.getId());
		}
	}

	@Override
	public String deleteById(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
			return "Deleted Employee id - " + id;
		} else {
			throw new NoSuchElementException("No employee with id " + id);
		}
	}

	@Override
	public List<Employee> fetchEmployeeByFirstName(String name) {
		List<Employee> employees = employeeRepository.findByFirstName(name);
		return employees;
	}

	@Override
	public List<Employee> fetchAllEmployees(String order) {
		Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

}
