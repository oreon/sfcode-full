package org.cerebrum.domain.customforms.action;

import org.cerebrum.domain.customforms.CustomField;
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

public class CustomFieldActionBase extends BaseAction<CustomField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CustomField customField;

	@DataModel
	private List<CustomField> customFieldList;

	@Factory("customFieldList")
	@Observer("archivedCustomField")
	public void findRecords() {
		search();
	}

	public CustomField getEntity() {
		return customField;
	}

	@Override
	public void setEntity(CustomField t) {
		this.customField = t;
	}

	@Override
	public void setEntityList(List<CustomField> list) {
		this.customFieldList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (customField.getCustomForm() != null) {
			criteria = criteria.add(Restrictions.eq("customForm.id",
					customField.getCustomForm().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<CustomField> getEntityList() {
		if (customFieldList == null) {
			findRecords();
		}
		return customFieldList;
	}

}
