package com.hrb.tservices.web.action.offices;

import com.hrb.tservices.domain.offices.OfficeLocatorMetrics;

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

public abstract class OfficeLocatorMetricsActionBase
		extends
			com.hrb.tservices.web.action.metrics.BaseMetricsAction<OfficeLocatorMetrics>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private OfficeLocatorMetrics officeLocatorMetrics;

	@DataModel
	private List<OfficeLocatorMetrics> officeLocatorMetricsRecordList;

	public void setOfficeLocatorMetricsId(Long id) {
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
	public void setOfficeLocatorMetricsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getOfficeLocatorMetricsId() {
		return (Long) getId();
	}

	public OfficeLocatorMetrics getEntity() {
		return officeLocatorMetrics;
	}

	//@Override
	public void setEntity(OfficeLocatorMetrics t) {
		this.officeLocatorMetrics = t;
		loadAssociations();
	}

	public OfficeLocatorMetrics getOfficeLocatorMetrics() {
		return (OfficeLocatorMetrics) getInstance();
	}

	@Override
	protected OfficeLocatorMetrics createInstance() {
		OfficeLocatorMetrics instance = super.createInstance();

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

	public OfficeLocatorMetrics getDefinedInstance() {
		return (OfficeLocatorMetrics) (isIdDefined() ? getInstance() : null);
	}

	public void setOfficeLocatorMetrics(OfficeLocatorMetrics t) {
		this.officeLocatorMetrics = t;
		if (officeLocatorMetrics != null)
			setOfficeLocatorMetricsId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<OfficeLocatorMetrics> getEntityClass() {
		return OfficeLocatorMetrics.class;
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

	public String viewOfficeLocatorMetrics() {
		load(currentEntityId);
		return "viewOfficeLocatorMetrics";
	}

}
