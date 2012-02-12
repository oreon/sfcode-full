package com.hrb.tservices.web.action.metrics;

import com.hrb.tservices.domain.metrics.RestMethod;

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

public abstract class RestMethodActionBase extends BaseAction<RestMethod>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RestMethod restMethod;

	@In(create = true, value = "restServiceAction")
	com.hrb.tservices.web.action.metrics.RestServiceAction restServiceAction;

	@DataModel
	private List<RestMethod> restMethodRecordList;

	public void setRestMethodId(Long id) {
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
	public void setRestMethodIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setRestServiceId(Long id) {

		if (id != null && id > 0)
			getInstance().setRestService(restServiceAction.loadFromId(id));

	}

	public Long getRestServiceId() {
		if (getInstance().getRestService() != null)
			return getInstance().getRestService().getId();
		return 0L;
	}

	public Long getRestMethodId() {
		return (Long) getId();
	}

	public RestMethod getEntity() {
		return restMethod;
	}

	//@Override
	public void setEntity(RestMethod t) {
		this.restMethod = t;
		loadAssociations();
	}

	public RestMethod getRestMethod() {
		return (RestMethod) getInstance();
	}

	@Override
	protected RestMethod createInstance() {
		RestMethod instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.metrics.RestService restService = restServiceAction
				.getDefinedInstance();
		if (restService != null && isNew()) {
			getInstance().setRestService(restService);
		}

	}

	public boolean isWired() {
		return true;
	}

	public RestMethod getDefinedInstance() {
		return (RestMethod) (isIdDefined() ? getInstance() : null);
	}

	public void setRestMethod(RestMethod t) {
		this.restMethod = t;
		if (restMethod != null)
			setRestMethodId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<RestMethod> getEntityClass() {
		return RestMethod.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (restMethod.getRestService() != null) {
			criteria = criteria.add(Restrictions.eq("restService.id",
					restMethod.getRestService().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (restMethod.getRestService() != null) {
			restServiceAction.setInstance(getInstance().getRestService());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewRestMethod() {
		load(currentEntityId);
		return "viewRestMethod";
	}

}
