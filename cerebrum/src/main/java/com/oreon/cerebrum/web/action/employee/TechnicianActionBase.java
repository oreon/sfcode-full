package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Technician;

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
import org.jboss.seam.annotations.security.Restrict;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

public abstract class TechnicianActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Technician>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Technician technician;

	public static final String DEFAULT_ROLE_NAME = "technician";

	public void setTechnicianId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		technician = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setTechnicianIdForModalDlg(Long id) {
		setId(id);
		technician = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getTechnicianId() {
		return (Long) getId();
	}

	public Technician getEntity() {
		return technician;
	}

	//@Override
	public void setEntity(Technician t) {
		this.technician = t;
		loadAssociations();
	}

	public Technician getTechnician() {
		return (Technician) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('technician', 'edit'}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('technician', 'delete'}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Technician createInstance() {
		Technician instance = super.createInstance();

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

	public Technician getDefinedInstance() {
		return (Technician) (isIdDefined() ? getInstance() : null);
	}

	public void setTechnician(Technician t) {
		this.technician = t;
		if (technician != null)
			setTechnicianId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Technician> getEntityClass() {
		return Technician.class;
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

	public Technician getCurrentLoggedInTechnician() {
		String query = "Select e from Technician e where e.appUser.userName = ?1";
		return (Technician) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String getDefaultRoleName() {
		return DEFAULT_ROLE_NAME;
	}

	public String viewTechnician() {
		load(currentEntityId);
		return "viewTechnician";
	}

}
