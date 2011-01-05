package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Subject;

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

public abstract class SubjectActionBase extends BaseAction<Subject>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Subject subject;

	@DataModel
	private List<Subject> subjectRecordList;

	public void setSubjectId(Long id) {
		if (id == 0) {
			clearInstance();
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
	public void setSubjectIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public Long getSubjectId() {
		return (Long) getId();
	}

	public Subject getEntity() {
		return subject;
	}

	//@Override
	public void setEntity(Subject t) {
		this.subject = t;
		loadAssociations();
	}

	public Subject getSubject() {
		return (Subject) getInstance();
	}

	@Override
	protected Subject createInstance() {
		return new Subject();
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

	public Subject getDefinedInstance() {
		return (Subject) (isIdDefined() ? getInstance() : null);
	}

	public void setSubject(Subject t) {
		this.subject = t;
		loadAssociations();
	}

	@Override
	public Class<Subject> getEntityClass() {
		return Subject.class;
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

}
