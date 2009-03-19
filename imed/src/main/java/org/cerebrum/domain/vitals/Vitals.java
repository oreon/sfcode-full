package org.cerebrum.domain.vitals;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "vitals")
@Name("vitals")
public class Vitals extends BusinessEntity {

	@Lob
	protected String general;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	protected org.cerebrum.domain.patient.Patient patient;

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getGeneral() {
		return general;
	}

	public void setPatient(org.cerebrum.domain.patient.Patient patient) {
		this.patient = patient;
	}

	public org.cerebrum.domain.patient.Patient getPatient() {
		return patient;
	}

	@Transient
	public String getDisplayName() {
		return general + "";
	}

}
