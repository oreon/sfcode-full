package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Settings;

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

public abstract class SettingsActionBase extends BaseAction<Settings>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Settings settings;

	@DataModel
	private List<Settings> settingsRecordList;

	public void setSettingsId(Long id) {
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
	public void setSettingsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getSettingsId() {
		return (Long) getId();
	}

	public Settings getEntity() {
		return settings;
	}

	//@Override
	public void setEntity(Settings t) {
		this.settings = t;
		loadAssociations();
	}

	public Settings getSettings() {
		return (Settings) getInstance();
	}

	@Override
	protected Settings createInstance() {
		Settings instance = super.createInstance();

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

	public Settings getDefinedInstance() {
		return (Settings) (isIdDefined() ? getInstance() : null);
	}

	public void setSettings(Settings t) {
		this.settings = t;
		if (settings != null)
			setSettingsId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Settings> getEntityClass() {
		return Settings.class;
	}

	public com.oreon.smartsis.domain.Settings findByUnqName(String name) {
		return executeSingleResultNamedQuery("settings.findByUnqName", name);
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

	public String viewSettings() {
		load(currentEntityId);
		return "viewSettings";
	}

}
