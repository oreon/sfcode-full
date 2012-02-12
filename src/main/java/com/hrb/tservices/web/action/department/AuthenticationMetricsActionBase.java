package com.hrb.tservices.web.action.department;

import com.hrb.tservices.domain.department.AuthenticationMetrics;

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

public abstract class AuthenticationMetricsActionBase
		extends
			com.hrb.tservices.web.action.metrics.BaseMetricsAction<AuthenticationMetrics>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AuthenticationMetrics authenticationMetrics;

	@DataModel
	private List<AuthenticationMetrics> authenticationMetricsRecordList;

	public void setAuthenticationMetricsId(Long id) {
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
	public void setAuthenticationMetricsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getAuthenticationMetricsId() {
		return (Long) getId();
	}

	public AuthenticationMetrics getEntity() {
		return authenticationMetrics;
	}

	//@Override
	public void setEntity(AuthenticationMetrics t) {
		this.authenticationMetrics = t;
		loadAssociations();
	}

	public AuthenticationMetrics getAuthenticationMetrics() {
		return (AuthenticationMetrics) getInstance();
	}

	@Override
	protected AuthenticationMetrics createInstance() {
		AuthenticationMetrics instance = super.createInstance();

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

	public AuthenticationMetrics getDefinedInstance() {
		return (AuthenticationMetrics) (isIdDefined() ? getInstance() : null);
	}

	public void setAuthenticationMetrics(AuthenticationMetrics t) {
		this.authenticationMetrics = t;
		if (authenticationMetrics != null)
			setAuthenticationMetricsId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<AuthenticationMetrics> getEntityClass() {
		return AuthenticationMetrics.class;
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

	public String viewAuthenticationMetrics() {
		load(currentEntityId);
		return "viewAuthenticationMetrics";
	}

}
