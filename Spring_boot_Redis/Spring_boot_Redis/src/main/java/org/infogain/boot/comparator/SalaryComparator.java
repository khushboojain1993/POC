package org.infogain.boot.comparator;

import java.util.Comparator;

import org.infogain.boot.model.Employee;



public class SalaryComparator implements Comparator<Employee> {

	

	@Override
	public int compare(Employee o1, Employee o2)
	{
		float salary1=o1.getSalary();
		float salary2=o2.getSalary();
		
		if(salary1==salary2)
		{
			return 0;
		}
		else if(salary1>salary2)
		{
			return 1;
		}
		else
		{
			return -1;
		}
		
	}

}
