package org.cerebrum.domain.customforms.action;

import org.cerebrum.domain.customforms.FilledForm;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

import org.cerebrum.domain.customforms.FilledField;

@Scope(ScopeType.CONVERSATION)
@Name("filledFormAction")
public class FilledFormAction extends BaseAction<FilledForm>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilledForm filledForm;

	@DataModel
	private List<FilledForm> filledFormList;

	@Factory("filledFormList")
	@Observer("archivedFilledForm")
	public void findRecords() {
		search();
	}

	public FilledForm getEntity() {
		return filledForm;
	}

	@Override
	public void setEntity(FilledForm t) {
		this.filledForm = t;
	}

	@Override
	public void setEntityList(List<FilledForm> list) {
		this.filledFormList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (filledForm.getCustomForm() != null) {
			criteria = criteria.add(Restrictions.eq("customForm.id", filledForm
					.getCustomForm().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<FilledField> listFilledFields;

	void initListFilledFields() {
		listFilledFields = new ArrayList<FilledField>();
		if (filledForm.getFilledFields().isEmpty()) {

		} else
			listFilledFields.addAll(filledForm.getFilledFields());
	}

	public List<FilledField> getListFilledFields() {
		if (listFilledFields == null) {
			initListFilledFields();
		}
		return listFilledFields;
	}

	public void setListFilledFields(List<FilledField> listFilledFields) {
		this.listFilledFields = listFilledFields;
	}

	public void deleteFilledFields(FilledField filledFields) {
		listFilledFields.remove(filledFields);
	}

	@Begin(join = true)
	public void addFilledFields() {
		FilledField filledFields = new FilledField();

		filledFields.setFilledForm(filledForm);

		listFilledFields.add(filledFields);
	}

	public void updateComposedAssociations() {

		filledForm.getFilledFields().clear();
		filledForm.getFilledFields().addAll(listFilledFields);

	}

	public List<FilledForm> getEntityList() {
		if (filledFormList == null) {
			findRecords();
		}
		return filledFormList;
	}

}
