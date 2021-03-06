package org.infogain.boot.controller;

import java.util.Collections;
import java.util.List;

import org.infogain.boot.comparator.NameComparator;
import org.infogain.boot.comparator.SalaryComparator;
import org.infogain.boot.dao.IEmployeeDao;
import org.infogain.boot.model.Employee;
import org.infogain.boot.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping(value = "/employee")
public class MainApp {

	@Autowired
	IEmployeeDao employeeDAO;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") String id) {
		Employee emp = employeeDAO.getEmployeeById(id);
		if(emp==null)
		{
			return new ResponseEntity<String>("Employee with given id doesn't exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> list = employeeDAO.getAllEmployees();
		if(list.isEmpty() || list.size() == 0) {
			return new ResponseEntity<String>("No employee exists in the record",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	@GetMapping("/name")
	public ResponseEntity<?> getEmployeesNameSorted() {
		List<Employee> list = employeeDAO.getAllEmployees();
		Collections.sort(list, new NameComparator());
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	@GetMapping("/salary")
	public ResponseEntity<?> getEmployeesSalarySorted() {
		List<Employee> list = employeeDAO.getAllEmployees();
		Collections.sort(list, new SalaryComparator());
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		boolean flag = employeeDAO.EmployeeExists(employee.getEmpId());
		if (flag) {
			return new ResponseEntity<String>("Employee Already exist!", HttpStatus.CONFLICT);
		}
		employeeDAO.addEmployee(employee);
		return new ResponseEntity<String>("Employee created successfully!", HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		
		boolean flag = employeeDAO.EmployeeExists(employee.getEmpId());
		if (!flag) {
			employeeDAO.addEmployee(employee);
			return new ResponseEntity<String>("New Employee created successfully!", HttpStatus.CREATED);
		}
		employeeDAO.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		Employee employee = employeeDAO.getEmployeeById(id);
		if (employee != null) {
			employeeDAO.deleteEmployee(employee);
			return new ResponseEntity<String>("Employee " + id + " deleted successfully!", HttpStatus.GONE);
		}
		return new ResponseEntity<String>("Employee " + id + " does not exist!", HttpStatus.NOT_FOUND);
	}

}
