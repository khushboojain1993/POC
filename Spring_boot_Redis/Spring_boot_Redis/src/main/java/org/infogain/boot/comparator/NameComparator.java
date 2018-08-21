package org.infogain.boot.comparator;

import java.util.Comparator;

import org.infogain.boot.model.Employee;



public class NameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getEmpName().compareTo(o2.getEmpName());
	}

}
