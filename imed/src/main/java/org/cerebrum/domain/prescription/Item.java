package org.cerebrum.domain.prescription;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "item")
@Name("item")
@Filter(name = "archiveFilterDef")
public class Item extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id", nullable = false)
	protected Prescription prescription;

	@Column(name = "qty", unique = false)
	protected Integer qty;

	protected String measurement;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "drug_id", nullable = false)
	protected org.cerebrum.domain.drug.Drug drug;

	protected Route route;

	protected String instructions;

	protected Boolean prn;

	@Column(name = "days", unique = false)
	protected Integer days;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "frequency_id", nullable = false)
	protected Frequency frequency;

	protected Boolean subs;

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setDrug(org.cerebrum.domain.drug.Drug drug) {
		this.drug = drug;
	}

	public org.cerebrum.domain.drug.Drug getDrug() {
		return drug;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Route getRoute() {
		return route;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setPrn(Boolean prn) {
		this.prn = prn;
	}

	public Boolean getPrn() {
		return prn;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getDays() {
		return days;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setSubs(Boolean subs) {
		this.subs = subs;
	}

	public Boolean getSubs() {
		return subs;
	}

	@Transient
	public String getDisplayName() {
		return prescription + "";
	}

}
