package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.Contact;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class ContactActionBase extends BaseAction<Contact>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Contact contact;

	@DataModel
	private List<Contact> contactRecordList;

	public void setContactId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getContactId() {
		return (Long) getId();
	}

	//@Factory("contactRecordList")
	//@Observer("archivedContact")
	public void findRecords() {
		//search();
	}

	public Contact getEntity() {
		return contact;
	}

	@Override
	public void setEntity(Contact t) {
		this.contact = t;
		loadAssociations();
	}

	public Contact getContact() {
		return getInstance();
	}

	@Override
	protected Contact createInstance() {
		return new Contact();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Contact getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setContact(Contact t) {
		this.contact = t;
		loadAssociations();
	}

	@Override
	public Class<Contact> getEntityClass() {
		return Contact.class;
	}

	@Override
	public void setEntityList(List<Contact> list) {
		this.contactRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Contact> getEntityList() {
		if (contactRecordList == null) {
			findRecords();
		}
		return contactRecordList;
	}

}
