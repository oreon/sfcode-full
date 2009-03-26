package org.cerebrum.domain.patient;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "allergy")
@Name("allergy")
@Filter(name = "archiveFilterDef")
public class Allergy extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String allergen;

	protected ReactionType reactionType;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	protected Patient patient;

	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}

	public String getAllergen() {
		return allergen;
	}

	public void setReactionType(ReactionType reactionType) {
		this.reactionType = reactionType;
	}

	public ReactionType getReactionType() {
		return reactionType;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	@Transient
	public String getDisplayName() {
		return allergen + "";
	}

}
