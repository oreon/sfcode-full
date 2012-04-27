package com.wc.shopper.domain;

import javax.persistence.Entity;

@Entity
public class Car extends BaseEntity {
	
	private String model;
	private String color;
	private String year;
	private String manufacturer;
	
	
	public String getModel() {
		return model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setModel(String model) {
		this.model = model;
	} 

}
