package org.infogain.boot.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.infogain.boot.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class EmployeeDao implements IEmployeeDao 
{

	private RedisTemplate<String, Employee> redisTemplateObject;
		
	private HashOperations hashOperatios;
	
	public EmployeeDao(RedisTemplate<String, Employee> redisTemplateObject)
	{
		this.redisTemplateObject=redisTemplateObject;
		hashOperatios=redisTemplateObject.opsForHash();
	}

	@Override
	public Map<String,Employee> getAllEmployees() {
		return hashOperatios.entries("EMPLOYEE");

	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return (Employee)hashOperatios.get("EMPLOYEE", employeeId);

	}

	@Override
	public void addEmployee(Employee employee) {
		hashOperatios.put("EMPLOYEE", employee.getEmpId(), employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		addEmployee(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		hashOperatios.delete("EMPLOYEE", employee.getEmpId());
	}

	@Override
	public boolean EmployeeExists(String employeeId) {
		EntityManager entityManager=null;
		return entityManager.createQuery("from Employee where Emp_Id=?").setParameter(1, employeeId).getResultList()
				.size() > 0 ? true : false;
	}

}
