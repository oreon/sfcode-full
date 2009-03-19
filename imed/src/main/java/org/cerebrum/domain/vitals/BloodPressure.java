package org.cerebrum.domain.vitals;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Embeddable
public class BloodPressure {

	protected Integer systolic;

	protected Integer diastolic;

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

}
