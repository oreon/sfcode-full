package com.hrb.tservices.web.action.offices;

import com.hrb.tservices.domain.offices.Office;

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

public abstract class OfficeActionBase extends BaseAction<Office>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Office office;

	@DataModel
	private List<Office> officeRecordList;

	public void setOfficeId(Long id) {
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
	public void setOfficeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getOfficeId() {
		return (Long) getId();
	}

	public Office getEntity() {
		return office;
	}

	//@Override
	public void setEntity(Office t) {
		this.office = t;
		loadAssociations();
	}

	public Office getOffice() {
		return (Office) getInstance();
	}

	@Override
	protected Office createInstance() {
		Office instance = super.createInstance();

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

	public Office getDefinedInstance() {
		return (Office) (isIdDefined() ? getInstance() : null);
	}

	public void setOffice(Office t) {
		this.office = t;
		if (office != null)
			setOfficeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Office> getEntityClass() {
		return Office.class;
	}

	public com.hrb.tservices.domain.offices.Office findByUnqOfficeId(
			String officeId) {
		return executeSingleResultNamedQuery("office.findByUnqOfficeId",
				officeId);
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

	public String viewOffice() {
		load(currentEntityId);
		return "viewOffice";
	}

}
