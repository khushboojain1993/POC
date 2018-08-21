package org.infogain.boot.util;

import java.util.List;
import java.util.Map;

import org.infogain.boot.model.Employee;

public interface IOperations {

	Map<String,Employee> getAllEmployees();

	Employee getEmployeeById(String employeeId);

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Employee employee);

	boolean EmployeeExists(String employeeId);
}