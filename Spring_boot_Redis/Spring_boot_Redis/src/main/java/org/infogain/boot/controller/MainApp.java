package org.infogain.boot.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.infogain.boot.comparator.NameComparator;
import org.infogain.boot.comparator.SalaryComparator;
import org.infogain.boot.model.Employee;

import org.infogain.boot.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	IEmployeeService employeeService;
	
	/*@Autowired
	Map<String,Employee> employeeMap;*/
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") String id) {
		Employee emp = employeeService.getEmployeeById(id);
		if(emp==null)
		{
			return new ResponseEntity<String>("Employee with given id doesn't exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);

	}

	/*@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		 employeeMap =  employeeService.getAllEmployees();
		if(employeeMap.isEmpty() || employeeMap.size() == 0) {
			return new ResponseEntity<String>("No employee exists in the record",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String,Employee>>(employeeMap, HttpStatus.OK);
	}*/
	
	
	@GetMapping("/name")
	public ResponseEntity<?> getEmployeesNameSorted() {
		 List<Employee> list = (List<Employee>) employeeService.getAllEmployees();
		Collections.sort(list, new NameComparator());
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	@GetMapping("/salary")
	public ResponseEntity<?> getEmployeesSalarySorted() {
		List<Employee> list = (List<Employee>) employeeService.getAllEmployees();
		Collections.sort(list, new SalaryComparator());
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		/*boolean flag = employeeService.EmployeeExists(employee.getEmpId());
		if (flag) {
			return new ResponseEntity<String>("Employee Already exist!", HttpStatus.CONFLICT);
		}*/
		employeeService.addEmployee(employee);
		return new ResponseEntity<String>("Employee created successfully!", HttpStatus.CREATED);
	}
	

	@PutMapping()
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		
		boolean flag = employeeService.EmployeeExists(employee.getEmpId());
		if (!flag) {
			employeeService.addEmployee(employee);
			return new ResponseEntity<String>("New Employee created successfully!", HttpStatus.CREATED);
		}
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			employeeService.deleteEmployee(employee);
			return new ResponseEntity<String>("Employee " + id + " deleted successfully!", HttpStatus.GONE);
		}
		return new ResponseEntity<String>("Employee " + id + " does not exist!", HttpStatus.NOT_FOUND);
	}

}
