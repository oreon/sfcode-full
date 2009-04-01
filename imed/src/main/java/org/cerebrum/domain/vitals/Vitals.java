package org.cerebrum.domain.vitals;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "vitals")
@Name("vitals")
@Filter(name = "archiveFilterDef")
public class Vitals extends BusinessEntity {

	@Lob
	protected String general;

	protected Pulse pulse = new Pulse();

	protected Temperature temperature = new Temperature();

	protected BloodPressure bloodPressure = new BloodPressure();

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getGeneral() {
		return general;
	}

	public void setPulse(Pulse pulse) {
		this.pulse = pulse;
	}

	public Pulse getPulse() {
		return pulse;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}

	@Transient
	public String getDisplayName() {
		return general + "";
	}

}
