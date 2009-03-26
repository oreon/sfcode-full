package org.cerebrum.domain.diseases.action;

import org.cerebrum.domain.diseases.Symptom;
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

import org.cerebrum.domain.diseases.Cause;

@Scope(ScopeType.CONVERSATION)
@Name("symptomAction")
public class SymptomAction extends BaseAction<Symptom>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Symptom symptom;

	@DataModel
	private List<Symptom> symptomList;

	@Factory("symptomList")
	@Observer("archivedSymptom")
	public void findRecords() {
		search();
	}

	public Symptom getEntity() {
		return symptom;
	}

	@Override
	public void setEntity(Symptom t) {
		this.symptom = t;
	}

	@Override
	public void setEntityList(List<Symptom> list) {
		this.symptomList = list;
	}

	public void updateAssociations() {

	}

	private List<Cause> listCauses;

	void initListCauses() {
		listCauses = new ArrayList<Cause>();
		if (symptom.getCauses().isEmpty()) {

		} else
			listCauses.addAll(symptom.getCauses());
	}

	public List<Cause> getListCauses() {
		if (listCauses == null) {
			initListCauses();
		}
		return listCauses;
	}

	public void setListCauses(List<Cause> listCauses) {
		this.listCauses = listCauses;
	}

	public void deleteCauses(Cause causes) {
		listCauses.remove(causes);
	}

	@Begin(join = true)
	public void addCauses() {
		Cause causes = new Cause();

		causes.setSymptom(symptom);

		listCauses.add(causes);
	}

	public void updateComposedAssociations() {

		symptom.getCauses().clear();
		symptom.getCauses().addAll(listCauses);

	}

	public List<Symptom> getEntityList() {
		if (symptomList == null) {
			findRecords();
		}
		return symptomList;
	}

}
