package org.cerebrum.domain.vitals;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Embeddable
public class Temperature {

	protected Double temprature;

	protected TemperatureType type;

	public void setTemprature(Double temprature) {
		this.temprature = temprature;
	}

	public Double getTemprature() {
		return temprature;
	}

	public void setType(TemperatureType type) {
		this.type = type;
	}

	public TemperatureType getType() {
		return type;
	}

}
