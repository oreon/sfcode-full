package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Release;

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

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class ReleaseActionBase extends BaseAction<Release>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Release release;

	@DataModel
	private List<Release> releaseRecordList;

	public void setReleaseId(Long id) {
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
	public void setReleaseIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getReleaseId() {
		return (Long) getId();
	}

	public Release getEntity() {
		return release;
	}

	//@Override
	public void setEntity(Release t) {
		this.release = t;
		loadAssociations();
	}

	public Release getRelease() {
		return (Release) getInstance();
	}

	@Override
	protected Release createInstance() {
		return new Release();
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

	public Release getDefinedInstance() {
		return (Release) (isIdDefined() ? getInstance() : null);
	}

	public void setRelease(Release t) {
		this.release = t;
		loadAssociations();
	}

	@Override
	public Class<Release> getEntityClass() {
		return Release.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListIssues();
		initListAvailableIssues();

	}

	public void updateAssociations() {

	}

	protected List<org.wc.trackrite.issues.Issue> listIssues = new ArrayList<org.wc.trackrite.issues.Issue>();

	void initListIssues() {

		if (listIssues.isEmpty())
			listIssues.addAll(getInstance().getIssues());

	}

	public List<org.wc.trackrite.issues.Issue> getListIssues() {

		prePopulateListIssues();
		return listIssues;
	}

	public void prePopulateListIssues() {
	}

	public void setListIssues(List<org.wc.trackrite.issues.Issue> listIssues) {
		this.listIssues = listIssues;
	}

	protected List<org.wc.trackrite.issues.Issue> listAvailableIssues = new ArrayList<org.wc.trackrite.issues.Issue>();

	void initListAvailableIssues() {

		listAvailableIssues = getEntityManager().createQuery(
				"select r from Issue r").getResultList();
		listAvailableIssues.removeAll(getInstance().getIssues());

	}

	@Begin(join = true)
	public List<org.wc.trackrite.issues.Issue> getListAvailableIssues() {

		prePopulateListAvailableIssues();
		return listAvailableIssues;
	}

	public void prePopulateListAvailableIssues() {
	}

	public void setListAvailableIssues(
			List<org.wc.trackrite.issues.Issue> listAvailableIssues) {
		this.listAvailableIssues = listAvailableIssues;
	}

	public void updateComposedAssociations() {

		if (listIssues != null) {
			getInstance().getIssues().clear();
			getInstance().getIssues().addAll(listIssues);
		}
	}

	public void clearLists() {

		listIssues.clear();

	}

}
