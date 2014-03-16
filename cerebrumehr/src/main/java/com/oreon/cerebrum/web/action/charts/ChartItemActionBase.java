package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.ChartItem;

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

//
public abstract class ChartItemActionBase extends BaseAction<ChartItem>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long chartItemId;

	@In(create = true, value = "chartAction")
	com.oreon.cerebrum.web.action.charts.ChartAction chartAction;

	public void setChartItemId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setChartItemIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setChartId(Long id) {

		if (id != null && id > 0)
			getInstance().setChart(chartAction.loadFromId(id));

	}

	public Long getChartId() {
		if (getInstance().getChart() != null)
			return getInstance().getChart().getId();
		return 0L;
	}

	public Long getChartItemId() {
		return (Long) getId();
	}

	public ChartItem getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(ChartItem t) {
		this.instance = t;
		loadAssociations();
	}

	public ChartItem getChartItem() {
		return (ChartItem) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('chartItem', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('chartItem', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ChartItem createInstance() {
		ChartItem instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.charts.Chart chart = chartAction
				.getDefinedInstance();
		if (chart != null && isNew()) {
			getInstance().setChart(chart);
		}

	}

	public ChartItem getDefinedInstance() {
		return (ChartItem) (isIdDefined() ? getInstance() : null);
	}

	public void setChartItem(ChartItem t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setChartItemId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<ChartItem> getEntityClass() {
		return ChartItem.class;
	}

	public com.oreon.cerebrum.charts.ChartItem findByUnqName(String name) {
		return executeSingleResultNamedQuery("chartItem.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getChart() != null) {
			criteria = criteria.add(Restrictions.eq("chart.id", instance
					.getChart().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getChart() != null) {
			chartAction.setInstance(getInstance().getChart());
			chartAction.loadAssociations();
		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewChartItem() {
		load(currentEntityId);
		return "viewChartItem";
	}

}
