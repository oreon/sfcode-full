package org.cerebrum.domain.encounter;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "complaint")
@Name("complaint")
@Filter(name = "archiveFilterDef")
public class Complaint extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", nullable = false)
	protected Encounter encounter;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "symptom_id", nullable = false)
	protected org.cerebrum.domain.diseases.Symptom symptom;

	protected ConditionType type;

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setSymptom(org.cerebrum.domain.diseases.Symptom symptom) {
		this.symptom = symptom;
	}

	public org.cerebrum.domain.diseases.Symptom getSymptom() {
		return symptom;
	}

	public void setType(ConditionType type) {
		this.type = type;
	}

	public ConditionType getType() {
		return type;
	}

	@Transient
	public String getDisplayName() {
		return encounter + "";
	}

}
