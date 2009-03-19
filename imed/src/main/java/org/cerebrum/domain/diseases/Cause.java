package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "cause")
@Name("cause")
public class Cause extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "symptom_id", nullable = false)
	protected Symptom symptom;

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	@Transient
	public String getDisplayName() {
		return symptom + "";
	}

}
