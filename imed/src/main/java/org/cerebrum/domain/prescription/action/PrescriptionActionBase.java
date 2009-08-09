package org.cerebrum.domain.prescription.action;

import org.cerebrum.domain.prescription.Prescription;
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

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

import org.cerebrum.domain.prescription.Item;

public class PrescriptionActionBase extends BaseAction<Prescription>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Prescription prescription;

	@DataModel
	private List<Prescription> prescriptionList;

	@Factory("prescriptionList")
	@Observer("archivedPrescription")
	public void findRecords() {
		search();
	}

	public Prescription getEntity() {
		return prescription;
	}

	@Override
	public void setEntity(Prescription t) {
		this.prescription = t;
	}

	@Override
	public void setEntityList(List<Prescription> list) {
		this.prescriptionList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (prescription.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", prescription
					.getPatient().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Item> listItems;

	void initListItems() {
		listItems = new ArrayList<Item>();
		if (prescription.getItems().isEmpty()) {

			addItems();

		} else
			listItems.addAll(prescription.getItems());
	}

	public List<Item> getListItems() {
		if (listItems == null) {
			initListItems();
		}
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	public void deleteItems(Item items) {
		listItems.remove(items);
	}

	@Begin(join = true)
	public void addItems() {
		Item items = new Item();

		items.setPrescription(prescription);

		listItems.add(items);
	}

	public void updateComposedAssociations() {

		prescription.getItems().clear();
		prescription.getItems().addAll(listItems);

	}

	public List<Prescription> getEntityList() {
		if (prescriptionList == null) {
			findRecords();
		}
		return prescriptionList;
	}

}
