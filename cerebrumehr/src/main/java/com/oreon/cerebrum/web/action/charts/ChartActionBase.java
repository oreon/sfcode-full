package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.Chart;

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
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.charts.ChartItem;

public abstract class ChartActionBase extends BaseAction<Chart>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long chartId;

	public void setChartId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setChartIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getChartId() {
		return (Long) getId();
	}

	public Chart getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Chart t) {
		this.instance = t;
		loadAssociations();
	}

	public Chart getChart() {
		return (Chart) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('chart', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('chart', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Chart createInstance() {
		Chart instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}

	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getChartItems().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListChartItems().add(
						new com.oreon.cerebrum.charts.ChartItem());
		}

	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Chart getDefinedInstance() {
		return (Chart) (isIdDefined() ? getInstance() : null);
	}

	public void setChart(Chart t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setChartId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Chart> getEntityClass() {
		return Chart.class;
	}

	public com.oreon.cerebrum.charts.Chart findByUnqName(String name) {
		return executeSingleResultNamedQuery("chart.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListChartItems();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.charts.ChartItem> listChartItems = new ArrayList<com.oreon.cerebrum.charts.ChartItem>();

	void initListChartItems() {

		if (listChartItems.isEmpty())
			listChartItems.addAll(getInstance().getChartItems());

	}

	public List<com.oreon.cerebrum.charts.ChartItem> getListChartItems() {

		prePopulateListChartItems();
		return listChartItems;
	}

	public void prePopulateListChartItems() {
	}

	public void setListChartItems(
			List<com.oreon.cerebrum.charts.ChartItem> listChartItems) {
		this.listChartItems = listChartItems;
	}

	public void deleteChartItems(int index) {
		listChartItems.remove(index);
	}

	@Begin(join = true)
	public void addChartItems() {

		initListChartItems();
		ChartItem chartItems = new ChartItem();

		chartItems.setChart(getInstance());

		getListChartItems().add(chartItems);

	}

	public void updateComposedAssociations() {

		if (listChartItems != null) {
			getInstance().getChartItems().clear();
			getInstance().getChartItems().addAll(listChartItems);
		}
	}

	public void clearLists() {
		listChartItems.clear();

	}

	public String viewChart() {
		load(currentEntityId);
		return "viewChart";
	}

}
