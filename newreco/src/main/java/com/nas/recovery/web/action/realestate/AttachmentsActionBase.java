package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Attachments;

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

public abstract class AttachmentsActionBase extends BaseAction<Attachments>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Attachments attachments;

	@DataModel
	private List<Attachments> attachmentsRecordList;

	public void setAttachmentsId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getAttachmentsId() {
		return (Long) getId();
	}

	//@Factory("attachmentsRecordList")
	//@Observer("archivedAttachments")
	public void findRecords() {
		//search();
	}

	public Attachments getEntity() {
		return attachments;
	}

	@Override
	public void setEntity(Attachments t) {
		this.attachments = t;
		loadAssociations();
	}

	public Attachments getAttachments() {
		return getInstance();
	}

	@Override
	protected Attachments createInstance() {
		return new Attachments();
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

	public Attachments getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setAttachments(Attachments t) {
		this.attachments = t;
		loadAssociations();
	}

	@Override
	public Class<Attachments> getEntityClass() {
		return Attachments.class;
	}

	@Override
	public void setEntityList(List<Attachments> list) {
		this.attachmentsRecordList = list;
	}

	public String downloadFile(Long id) {
		setId(id);
		downloadAttachment(getInstance().getFile());
		return "success";
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public List<Attachments> getEntityList() {
		if (attachmentsRecordList == null) {
			findRecords();
		}
		return attachmentsRecordList;
	}

}
