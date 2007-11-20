package org.witchcraft.model.data.location;

import javax.persistence.MappedSuperclass;

/**
 * Class representing countries , A class in a uml model deriving 
 * from this class will get all the fields and running test data
 * generator will add all iso countries to the database
 * @author jsingh
 *
 */
@MappedSuperclass
public class Country {
	
	private String countryName;
	private String isoCode;
	private int number;
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Country(String countryName, String isoCode, int number) {
		super();
		this.countryName = countryName;
		this.isoCode = isoCode;
		this.number = number;
	}
	
	

}
