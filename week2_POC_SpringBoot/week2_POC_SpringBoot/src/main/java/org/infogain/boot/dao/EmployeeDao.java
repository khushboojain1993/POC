package org.infogain.boot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.infogain.boot.model.Employee;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class EmployeeDao implements IEmployeeDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) entityManager.createQuery("from Employee").getResultList();

	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return entityManager.find(Employee.class, employeeId);

	}

	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee emp = getEmployeeById(employee.getEmpId());

		emp.setEmpId(employee.getEmpId());
		emp.setEmpName(employee.getEmpName());
		emp.setSalary(employee.getSalary());
		emp.setEmpaddress(employee.getEmpaddress());
		entityManager.flush();
	}

	@Override
	public void deleteEmployee(Employee employee) {
		entityManager.remove(employee);

	}

	@Override
	public boolean EmployeeExists(String employeeId) {
		return entityManager.createQuery("from Employee where Emp_Id=?").setParameter(1, employeeId).getResultList()
				.size() > 0 ? true : false;
	}

}
