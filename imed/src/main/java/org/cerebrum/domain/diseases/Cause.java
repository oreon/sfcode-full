package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "cause")
@Name("cause")
@Filter(name = "archiveFilterDef")
public class Cause extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "symptom_id", nullable = false)
	protected Symptom symptom;

	protected String name;

	@Lob
	protected String description;

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Transient
	public String getDisplayName() {
		return symptom + "";
	}

}
