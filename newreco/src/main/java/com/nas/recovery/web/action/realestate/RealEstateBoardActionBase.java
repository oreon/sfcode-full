package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateBoard;

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

import com.nas.recovery.domain.realestate.Attachments;

public abstract class RealEstateBoardActionBase
		extends
			BaseAction<RealEstateBoard> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RealEstateBoard realEstateBoard;

	@DataModel
	private List<RealEstateBoard> realEstateBoardRecordList;

	public void setRealEstateBoardId(Long id) {

		if (listAttachmentses == null || isDifferentFromCurrent(id))
			listAttachmentses = new ArrayList<Attachments>();

		setId(id);
		loadAssociations();
	}

	public Long getRealEstateBoardId() {
		return (Long) getId();
	}

	//@Factory("realEstateBoardRecordList")
	//@Observer("archivedRealEstateBoard")
	public void findRecords() {
		//search();
	}

	public RealEstateBoard getEntity() {
		return realEstateBoard;
	}

	@Override
	public void setEntity(RealEstateBoard t) {
		this.realEstateBoard = t;
		loadAssociations();
	}

	public RealEstateBoard getRealEstateBoard() {
		return getInstance();
	}

	@Override
	protected RealEstateBoard createInstance() {
		return new RealEstateBoard();
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

	public RealEstateBoard getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setRealEstateBoard(RealEstateBoard t) {
		this.realEstateBoard = t;
		loadAssociations();
	}

	@Override
	public Class<RealEstateBoard> getEntityClass() {
		return RealEstateBoard.class;
	}

	@Override
	public void setEntityList(List<RealEstateBoard> list) {
		this.realEstateBoardRecordList = list;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	protected List<com.nas.recovery.domain.realestate.Attachments> listAttachmentses;

	void initListAttachmentses() {
		listAttachmentses = new ArrayList<com.nas.recovery.domain.realestate.Attachments>();

		if (getInstance().getAttachmentses().isEmpty()) {

		} else
			listAttachmentses.addAll(getInstance().getAttachmentses());

	}

	public List<com.nas.recovery.domain.realestate.Attachments> getListAttachmentses() {
		if (listAttachmentses == null || listAttachmentses.isEmpty()) {
			initListAttachmentses();
		}
		return listAttachmentses;
	}

	public void setListAttachmentses(
			List<com.nas.recovery.domain.realestate.Attachments> listAttachmentses) {
		this.listAttachmentses = listAttachmentses;
	}

	public void deleteAttachmentses(int index) {
		listAttachmentses.remove(index);
	}

	@Begin(join = true)
	public void addAttachmentses() {
		Attachments attachmentses = new Attachments();

		listAttachmentses.add(attachmentses);
	}

	public void updateComposedAssociations() {

		if (listAttachmentses != null) {
			getInstance().getAttachmentses().clear();
			getInstance().getAttachmentses().addAll(listAttachmentses);
		}
	}

	public List<RealEstateBoard> getEntityList() {
		if (realEstateBoardRecordList == null) {
			findRecords();
		}
		return realEstateBoardRecordList;
	}

}
