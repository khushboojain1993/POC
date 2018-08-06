package org.infogain.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee 
{

	@Id
	@Column(name = "Emp_Id",unique=true)
	private String empId;
	
	@Column(name = "Emp_Name", nullable = false)
	private String empName;
	
	@Column(name = "Emp_Salary", nullable = false)
	private float salary;

	private EmpAddress empaddress;

	public Employee() {
		// System.out.println("This is default constructor");
	}

	public Employee(String empId, String empName, float salary, EmpAddress empaddress) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.empaddress = empaddress;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public EmpAddress getEmpaddress() {
		return empaddress;
	}

	public void setEmpaddress(EmpAddress empaddress) {
		this.empaddress = empaddress;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + ", Address=" + empaddress
				+ "]";
	}

}
