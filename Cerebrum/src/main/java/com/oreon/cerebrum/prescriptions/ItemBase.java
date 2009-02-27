
/**
 * This is generated code - to edit code or override methods use - Item class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.prescriptions;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public abstract class ItemBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	protected Route route;

	protected Frequency frequency;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String qty;

	/* Default Constructor */
	public ItemBase() {
	}

	/* Constructor with all attributes */
	public ItemBase(Route route, Frequency frequency, String qty) {

		this.route = route;

		this.frequency = frequency;

		this.qty = qty;

	}

	public Route getRoute() {
		return this.route;
	}

	public Frequency getFrequency() {
		return this.frequency;
	}

	public String getQty() {
		return this.qty;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	private com.oreon.cerebrum.prescriptions.Prescription prescription = new com.oreon.cerebrum.prescriptions.Prescription();

	private com.oreon.cerebrum.drugs.Drug drug;

	@ManyToOne
	@JoinColumn(name = "prescription_ID", nullable = false, updatable = true)
	@ContainedIn
	@XmlTransient
	public com.oreon.cerebrum.prescriptions.Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(
			com.oreon.cerebrum.prescriptions.Prescription prescription) {
		this.prescription = prescription;
	}

	@ManyToOne
	@JoinColumn(name = "drug_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.cerebrum.drugs.Drug getDrug() {
		return this.drug;
	}

	public void setDrug(com.oreon.cerebrum.drugs.Drug drug) {
		this.drug = drug;
	}

	public abstract Item itemInstance();

	@Transient
	public String getDisplayName() {
		return route + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("qty");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
