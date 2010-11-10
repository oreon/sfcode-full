package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Candidate;

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

public abstract class CandidateActionBase extends BaseAction<Candidate>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Candidate candidate;

	@DataModel
	private List<Candidate> candidateRecordList;

	public void setCandidateId(Long id) {
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCandidateIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getCandidateId() {
		return (Long) getId();
	}

	//@Factory("candidateRecordList")
	//@Observer("archivedCandidate")
	public void findRecords() {
		//search();
	}

	public Candidate getEntity() {
		return candidate;
	}

	@Override
	public void setEntity(Candidate t) {
		this.candidate = t;
		loadAssociations();
	}

	public Candidate getCandidate() {
		return getInstance();
	}

	@Override
	protected Candidate createInstance() {
		return new Candidate();
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

	public Candidate getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setCandidate(Candidate t) {
		this.candidate = t;
		loadAssociations();
	}

	@Override
	public Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	@Override
	public void setEntityList(List<Candidate> list) {
		this.candidateRecordList = list;
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

	public List<Candidate> getEntityList() {
		if (candidateRecordList == null) {
			findRecords();
		}
		return candidateRecordList;
	}

}
