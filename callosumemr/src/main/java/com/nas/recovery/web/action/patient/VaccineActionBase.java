package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Vaccine;

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
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class VaccineActionBase extends BaseAction<Vaccine>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Vaccine vaccine;

	@DataModel
	private List<Vaccine> vaccineRecordList;

	public void setVaccineId(Long id) {
		if (id == 0) {
			clearInstance();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setVaccineIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getVaccineId() {
		return (Long) getId();
	}

	public Vaccine getEntity() {
		return vaccine;
	}

	//@Override
	public void setEntity(Vaccine t) {
		this.vaccine = t;
		loadAssociations();
	}

	public Vaccine getVaccine() {
		return (Vaccine) getInstance();
	}

	@Override
	protected Vaccine createInstance() {
		return new Vaccine();
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

	public Vaccine getDefinedInstance() {
		return (Vaccine) (isIdDefined() ? getInstance() : null);
	}

	public void setVaccine(Vaccine t) {
		this.vaccine = t;
		loadAssociations();
	}

	@Override
	public Class<Vaccine> getEntityClass() {
		return Vaccine.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

}
