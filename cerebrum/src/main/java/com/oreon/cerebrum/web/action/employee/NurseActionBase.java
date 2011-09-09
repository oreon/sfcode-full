package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Nurse;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class NurseActionBase extends BaseAction<Nurse>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Nurse nurse;

	@DataModel
	private List<Nurse> nurseRecordList;

	public void setNurseId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
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
	public void setNurseIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getNurseId() {
		return (Long) getId();
	}

	public Nurse getEntity() {
		return nurse;
	}

	//@Override
	public void setEntity(Nurse t) {
		this.nurse = t;
		loadAssociations();
	}

	public Nurse getNurse() {
		return (Nurse) getInstance();
	}

	@Override
	protected Nurse createInstance() {
		Nurse instance = super.createInstance();

		return instance;
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

	public Nurse getDefinedInstance() {
		return (Nurse) (isIdDefined() ? getInstance() : null);
	}

	public void setNurse(Nurse t) {
		this.nurse = t;
		if (nurse != null)
			setNurseId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Nurse> getEntityClass() {
		return Nurse.class;
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

	public void clearLists() {

	}

	public Nurse getCurrentLoggedInNurse() {
		String query = "Select e from Nurse e where e.appUser.userName = ?1";
		return (Nurse) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String viewNurse() {
		load(currentEntityId);
		return "viewNurse";
	}

}
