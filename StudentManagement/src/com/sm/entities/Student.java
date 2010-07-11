package com.sm.entities;

import java.util.Date;

public class Student {
	private String firstName;
	private String lastName;
	private Date dob;
	private int yob;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getFormattedName(){
		if (lastName == null ) 
			lastName = "";
		return firstName + "," + lastName;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	public int getYob() {
		
		return yob;
	}
	
	public int getAge(){
		Date dt = new Date();
		int yr = dt.getYear() + 1900;
		return yr - yob;
		
	}
	

}
