package org.witchcraft.model.data.location;

import javax.persistence.Entity;

import org.witchcraft.model.support.BusinessEntity;

@Entity
public class State extends BusinessEntity{
	
	private Country country;
	private String name;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State(Country country, String name) {
		super();
		this.country = country;
		this.name = name;
	}

}
