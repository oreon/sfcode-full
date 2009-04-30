package org.caisi;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Student {
	private String firstName;
	private String lastName;
	private int age;
	

	private Address address; //= new Address();
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student(){
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Student(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
	     return new ToStringBuilder(this).
	       append("firstName", firstName).
	       append("age", age).
	       append("lastName", lastName).
	       toString();
	   }

}
