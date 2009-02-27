package com.oreon.cerebrum.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.cerebrum.prescriptions.Prescription;
import com.oreon.cerebrum.service.PrescriptionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

import com.oreon.cerebrum.prescriptions.Item;

/**
 * This is generated code - to edit code or override methods use - Prescription class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class PrescriptionBackingBeanBase extends BaseBackingBean<Prescription> {

	protected Prescription prescription = new Prescription();

	protected PrescriptionService prescriptionService;

	private List<Item> listItems = new ArrayList<Item>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}

	public PrescriptionService getPrescriptionService() {
		return this.prescriptionService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Prescription> getBaseService() {
		return prescriptionService;
	}

	public Prescription getEntity() {
		return getPrescription();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		prescription = new Prescription();
		resetRanges();

		listItems.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			prescription = prescriptionService.load(id);

	}

	@Override
	public String update() {

		addItemsToPrescription();

		return super.update();
	}

	public List<Item> getListItems() {
		if (listItems.isEmpty())
			loadItems();

		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	private void loadItems() {
		listItems.clear();
		if (prescription != null) {
			listItems.addAll(prescription.getItem());
		}
		int sizeOfExistingElements = listItems.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listItems.add(new Item());
		}
	}

	private void addItemsToPrescription() {
		prescription = prescriptionService.save(prescription);//To prevent lazy initialization exception
		prescription.getItem().clear();
		List<Item> listValidItems = new ArrayList<Item>();

		for (Item item : listItems) {

			item.setPrescription(prescription);
			listValidItems.add(item);

		}

		prescription.getItem().addAll(listValidItems);
		resetCachedLists();
	}

	/**
	 * @param actionEvent
	 */
	public void addNewItemRow(ActionEvent actionEvent) {
		listItems.add(new Item());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteItemRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		Item item = listItems.get(index);
		listItems.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
