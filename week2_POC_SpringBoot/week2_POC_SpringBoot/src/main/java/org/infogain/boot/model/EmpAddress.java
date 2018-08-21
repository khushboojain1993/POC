package org.infogain.boot.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmpAddress

{
	String  city, state, country;
	int houseNumber;

	
	public EmpAddress() {
		super();
	}

	public EmpAddress(int houseNumber, String city, String state, String country) {
		super();
		this.houseNumber = houseNumber;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "EmpAddress [houseNumber=" + houseNumber + ", city=" + city + ", state=" + state + ", country=" + country
				+ "]";
	}
	
	
}
