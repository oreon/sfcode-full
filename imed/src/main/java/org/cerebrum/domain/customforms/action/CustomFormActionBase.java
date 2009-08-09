package org.cerebrum.domain.customforms.action;

import org.cerebrum.domain.customforms.CustomForm;
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

import org.cerebrum.domain.customforms.CustomField;

public class CustomFormActionBase extends BaseAction<CustomForm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomForm customForm;

	@DataModel
	private List<CustomForm> customFormList;

	@Factory("customFormList")
	@Observer("archivedCustomForm")
	public void findRecords() {
		search();
	}

	public CustomForm getEntity() {
		return customForm;
	}

	@Override
	public void setEntity(CustomForm t) {
		this.customForm = t;
	}

	@Override
	public void setEntityList(List<CustomForm> list) {
		this.customFormList = list;
	}

	public void updateAssociations() {

	}

	private List<CustomField> listCustomFields;

	void initListCustomFields() {
		listCustomFields = new ArrayList<CustomField>();
		if (customForm.getCustomFields().isEmpty()) {

			addCustomFields();

		} else
			listCustomFields.addAll(customForm.getCustomFields());
	}

	public List<CustomField> getListCustomFields() {
		if (listCustomFields == null) {
			initListCustomFields();
		}
		return listCustomFields;
	}

	public void setListCustomFields(List<CustomField> listCustomFields) {
		this.listCustomFields = listCustomFields;
	}

	public void deleteCustomFields(CustomField customFields) {
		listCustomFields.remove(customFields);
	}

	@Begin(join = true)
	public void addCustomFields() {
		CustomField customFields = new CustomField();

		customFields.setCustomForm(customForm);

		listCustomFields.add(customFields);
	}

	public void updateComposedAssociations() {

		customForm.getCustomFields().clear();
		customForm.getCustomFields().addAll(listCustomFields);

	}

	public List<CustomForm> getEntityList() {
		if (customFormList == null) {
			findRecords();
		}
		return customFormList;
	}

}
