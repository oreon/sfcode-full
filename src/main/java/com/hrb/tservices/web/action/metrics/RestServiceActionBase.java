package com.hrb.tservices.web.action.metrics;

import com.hrb.tservices.domain.metrics.RestService;

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

import com.hrb.tservices.domain.metrics.RestMethod;

public abstract class RestServiceActionBase extends BaseAction<RestService>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RestService restService;

	@DataModel
	private List<RestService> restServiceRecordList;

	public void setRestServiceId(Long id) {
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
	public void setRestServiceIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getRestServiceId() {
		return (Long) getId();
	}

	public RestService getEntity() {
		return restService;
	}

	//@Override
	public void setEntity(RestService t) {
		this.restService = t;
		loadAssociations();
	}

	public RestService getRestService() {
		return (RestService) getInstance();
	}

	@Override
	protected RestService createInstance() {
		RestService instance = super.createInstance();

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

	public RestService getDefinedInstance() {
		return (RestService) (isIdDefined() ? getInstance() : null);
	}

	public void setRestService(RestService t) {
		this.restService = t;
		if (restService != null)
			setRestServiceId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<RestService> getEntityClass() {
		return RestService.class;
	}

	public com.hrb.tservices.domain.metrics.RestService findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("restService.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListRestMethods();

	}

	public void updateAssociations() {

	}

	protected List<com.hrb.tservices.domain.metrics.RestMethod> listRestMethods = new ArrayList<com.hrb.tservices.domain.metrics.RestMethod>();

	void initListRestMethods() {

		if (listRestMethods.isEmpty())
			listRestMethods.addAll(getInstance().getRestMethods());

	}

	public List<com.hrb.tservices.domain.metrics.RestMethod> getListRestMethods() {

		prePopulateListRestMethods();
		return listRestMethods;
	}

	public void prePopulateListRestMethods() {
	}

	public void setListRestMethods(
			List<com.hrb.tservices.domain.metrics.RestMethod> listRestMethods) {
		this.listRestMethods = listRestMethods;
	}

	public void deleteRestMethods(int index) {
		listRestMethods.remove(index);
	}

	@Begin(join = true)
	public void addRestMethods() {
		initListRestMethods();
		RestMethod restMethods = new RestMethod();

		restMethods.setRestService(getInstance());

		getListRestMethods().add(restMethods);
	}

	public void updateComposedAssociations() {

		if (listRestMethods != null) {
			getInstance().getRestMethods().clear();
			getInstance().getRestMethods().addAll(listRestMethods);
		}
	}

	public void clearLists() {
		listRestMethods.clear();

	}

	public String viewRestService() {
		load(currentEntityId);
		return "viewRestService";
	}

}
