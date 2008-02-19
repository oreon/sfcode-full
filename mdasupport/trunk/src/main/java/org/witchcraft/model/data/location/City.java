package org.witchcraft.model.data.location;

import javax.persistence.Entity;

import org.witchcraft.model.support.BusinessEntity;

@Entity
public class City extends BusinessEntity{
	
	private State state;
	
	private String name;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
