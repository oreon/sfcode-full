package com.pcas.datapkg.web.action.dashboards;

import com.pcas.datapkg.dashboards.Dashboard;

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

import com.pcas.datapkg.dashboards.DashboardComponent;

public abstract class DashboardActionBase extends BaseAction<Dashboard>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Dashboard dashboard;

	@In(create = true, value = "appUserAction")
	com.pcas.datapkg.web.action.users.AppUserAction appUserAction;

	@DataModel
	private List<Dashboard> dashboardRecordList;

	public void setDashboardId(Long id) {
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
	public void setDashboardIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
		return 0L;
	}

	public Long getDashboardId() {
		return (Long) getId();
	}

	public Dashboard getEntity() {
		return dashboard;
	}

	//@Override
	public void setEntity(Dashboard t) {
		this.dashboard = t;
		loadAssociations();
	}

	public Dashboard getDashboard() {
		return (Dashboard) getInstance();
	}

	@Override
	protected Dashboard createInstance() {
		Dashboard instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Dashboard getDefinedInstance() {
		return (Dashboard) (isIdDefined() ? getInstance() : null);
	}

	public void setDashboard(Dashboard t) {
		this.dashboard = t;
		if (dashboard != null)
			setDashboardId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Dashboard> getEntityClass() {
		return Dashboard.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (dashboard.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", dashboard
					.getAppUser().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (dashboard.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
		}

		initListDashboardComponents();

	}

	public void updateAssociations() {

	}

	protected List<com.pcas.datapkg.dashboards.DashboardComponent> listDashboardComponents = new ArrayList<com.pcas.datapkg.dashboards.DashboardComponent>();

	void initListDashboardComponents() {

		if (listDashboardComponents.isEmpty())
			listDashboardComponents.addAll(getInstance()
					.getDashboardComponents());

	}

	public List<com.pcas.datapkg.dashboards.DashboardComponent> getListDashboardComponents() {

		prePopulateListDashboardComponents();
		return listDashboardComponents;
	}

	public void prePopulateListDashboardComponents() {
	}

	public void setListDashboardComponents(
			List<com.pcas.datapkg.dashboards.DashboardComponent> listDashboardComponents) {
		this.listDashboardComponents = listDashboardComponents;
	}

	public void deleteDashboardComponents(int index) {
		listDashboardComponents.remove(index);
	}

	@Begin(join = true)
	public void addDashboardComponents() {
		initListDashboardComponents();
		DashboardComponent dashboardComponents = new DashboardComponent();

		dashboardComponents.setDashboard(getInstance());

		getListDashboardComponents().add(dashboardComponents);
	}

	public void updateComposedAssociations() {

		if (listDashboardComponents != null) {
			getInstance().getDashboardComponents().clear();
			getInstance().getDashboardComponents().addAll(
					listDashboardComponents);
		}
	}

	public void clearLists() {
		listDashboardComponents.clear();

	}

	public String viewDashboard() {
		load(currentEntityId);
		return "viewDashboard";
	}

}
