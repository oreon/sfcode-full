package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.FilesUploaded;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public abstract class FilesUploadedActionBase extends BaseAction<FilesUploaded>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilesUploaded filesUploaded;

	@In(create = true, value = "realEstatePropertyAction")
	com.nas.recovery.web.action.realestate.RealEstatePropertyAction realEstatePropertyAction;

	@DataModel
	private List<FilesUploaded> filesUploadedRecordList;

	public void setFilesUploadedId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setRealEstatePropertyId(Long id) {
		if (id != null && id > 0)
			getInstance().setRealEstateProperty(
					realEstatePropertyAction.loadFromId(id));
	}

	public Long getRealEstatePropertyId() {
		if (getInstance().getRealEstateProperty() != null)
			return getInstance().getRealEstateProperty().getId();
		return 0L;
	}

	public Long getFilesUploadedId() {
		return (Long) getId();
	}

	//@Factory("filesUploadedRecordList")
	//@Observer("archivedFilesUploaded")
	public void findRecords() {
		//search();
	}

	public FilesUploaded getEntity() {
		return filesUploaded;
	}

	@Override
	public void setEntity(FilesUploaded t) {
		this.filesUploaded = t;
		loadAssociations();
	}

	public FilesUploaded getFilesUploaded() {
		return getInstance();
	}

	@Override
	protected FilesUploaded createInstance() {
		return new FilesUploaded();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty = realEstatePropertyAction
				.getDefinedInstance();
		if (realEstateProperty != null) {
			getInstance().setRealEstateProperty(realEstateProperty);
		}

	}

	public boolean isWired() {
		return true;
	}

	public FilesUploaded getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setFilesUploaded(FilesUploaded t) {
		this.filesUploaded = t;
		loadAssociations();
	}

	@Override
	public Class<FilesUploaded> getEntityClass() {
		return FilesUploaded.class;
	}

	@Override
	public void setEntityList(List<FilesUploaded> list) {
		this.filesUploadedRecordList = list;
	}

	public String downloadFile(Long id) {
		setId(id);
		downloadAttachment(getInstance().getFile());
		return "success";
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (filesUploaded.getRealEstateProperty() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateProperty.id",
					filesUploaded.getRealEstateProperty().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (filesUploaded.getRealEstateProperty() != null) {
			realEstatePropertyAction.setInstance(getInstance()
					.getRealEstateProperty());
		}

	}

	public void updateAssociations() {

	}

	public List<FilesUploaded> getEntityList() {
		if (filesUploadedRecordList == null) {
			findRecords();
		}
		return filesUploadedRecordList;
	}

}
