
/**
 * This is generated code - to edit code or override methods use - Prescription class
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
public abstract class PrescriptionBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String dx;

	/* Default Constructor */
	public PrescriptionBase() {
	}

	/* Constructor with all attributes */
	public PrescriptionBase(String dx) {

		this.dx = dx;

	}

	public String getDx() {
		return this.dx;
	}

	public void setDx(String dx) {
		this.dx = dx;
	}

	private java.util.Set<com.oreon.cerebrum.prescriptions.Item> item = new java.util.HashSet<com.oreon.cerebrum.prescriptions.Item>();

	private com.oreon.cerebrum.prescriptions.Patient patient;

	@ManyToOne
	@JoinColumn(name = "patient_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.cerebrum.prescriptions.Patient getPatient() {
		return this.patient;
	}

	public void setPatient(com.oreon.cerebrum.prescriptions.Patient patient) {
		this.patient = patient;
	}

	public void addItem(com.oreon.cerebrum.prescriptions.Item item) {
		checkMaximumItem();
		item.setPrescription(prescriptionInstance());
		this.item.add(item);
	}

	public void remove(com.oreon.cerebrum.prescriptions.Item item) {
		this.item.remove(item);
	}

	@OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JoinColumn(name = "prescription_ID", nullable = true)
	public java.util.Set<com.oreon.cerebrum.prescriptions.Item> getItem() {
		return this.item;
	}

	public void setItem(
			java.util.Set<com.oreon.cerebrum.prescriptions.Item> item) {
		this.item = item;
	}

	@Transient
	public java.util.Iterator<com.oreon.cerebrum.prescriptions.Item> getItemIterator() {
		return this.item.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getItemCount() {
		return this.item.size();
	}

	public void checkMaximumItem() {
		// if(item.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + item );
	}

	public abstract Prescription prescriptionInstance();

	@Transient
	public String getDisplayName() {
		return dx + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("dx");

		listSearchableFields.add("item.qty");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
