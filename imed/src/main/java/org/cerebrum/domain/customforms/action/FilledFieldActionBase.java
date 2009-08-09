package org.cerebrum.domain.customforms.action;

import org.cerebrum.domain.customforms.FilledField;
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

public class FilledFieldActionBase extends BaseAction<FilledField>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilledField filledField;

	@DataModel
	private List<FilledField> filledFieldList;

	@Factory("filledFieldList")
	@Observer("archivedFilledField")
	public void findRecords() {
		search();
	}

	public FilledField getEntity() {
		return filledField;
	}

	@Override
	public void setEntity(FilledField t) {
		this.filledField = t;
	}

	@Override
	public void setEntityList(List<FilledField> list) {
		this.filledFieldList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (filledField.getCustomField() != null) {
			criteria = criteria.add(Restrictions.eq("customField.id",
					filledField.getCustomField().getId()));
		}

		if (filledField.getFilledForm() != null) {
			criteria = criteria.add(Restrictions.eq("filledForm.id",
					filledField.getFilledForm().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<FilledField> getEntityList() {
		if (filledFieldList == null) {
			findRecords();
		}
		return filledFieldList;
	}

}
