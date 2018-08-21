package org.infogain.boot.services;

import java.util.List;

import org.infogain.boot.dao.IEmployeeDao;
import org.infogain.boot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDAO;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return employeeDAO.getEmployeeById(employeeId);
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);

	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeDAO.deleteEmployee(employee);

	}

	@Override
	public boolean EmployeeExists(String employeeId) {
		return employeeDAO.EmployeeExists(employeeId);
	}

}
