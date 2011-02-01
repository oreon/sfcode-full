package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Sponsorship;

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

public abstract class SponsorshipActionBase extends BaseAction<Sponsorship>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Sponsorship sponsorship;

	@In(create = true, value = "studentAction")
	com.nas.recovery.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "sponsororAction")
	com.nas.recovery.web.action.domain.SponsororAction sponsororAction;

	@DataModel
	private List<Sponsorship> sponsorshipRecordList;

	public void setSponsorshipId(Long id) {
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
	public void setSponsorshipIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setStudentId(Long id) {

		if (id != null && id > 0)
			getInstance().setStudent(studentAction.loadFromId(id));

	}

	public Long getStudentId() {
		if (getInstance().getStudent() != null)
			return getInstance().getStudent().getId();
		return 0L;
	}

	public void setSponsororId(Long id) {

		if (id != null && id > 0)
			getInstance().setSponsoror(sponsororAction.loadFromId(id));

	}

	public Long getSponsororId() {
		if (getInstance().getSponsoror() != null)
			return getInstance().getSponsoror().getId();
		return 0L;
	}

	public Long getSponsorshipId() {
		return (Long) getId();
	}

	public Sponsorship getEntity() {
		return sponsorship;
	}

	//@Override
	public void setEntity(Sponsorship t) {
		this.sponsorship = t;
		loadAssociations();
	}

	public Sponsorship getSponsorship() {
		return (Sponsorship) getInstance();
	}

	@Override
	protected Sponsorship createInstance() {
		return new Sponsorship();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		org.wc.mytapovan.domain.Student student = studentAction
				.getDefinedInstance();
		if (student != null && isNew()) {
			getInstance().setStudent(student);
		}

		org.wc.mytapovan.domain.Sponsoror sponsoror = sponsororAction
				.getDefinedInstance();
		if (sponsoror != null && isNew()) {
			getInstance().setSponsoror(sponsoror);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Sponsorship getDefinedInstance() {
		return (Sponsorship) (isIdDefined() ? getInstance() : null);
	}

	public void setSponsorship(Sponsorship t) {
		this.sponsorship = t;
		loadAssociations();
	}

	@Override
	public Class<Sponsorship> getEntityClass() {
		return Sponsorship.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (sponsorship.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id", sponsorship
					.getStudent().getId()));
		}

		if (sponsorship.getSponsoror() != null) {
			criteria = criteria.add(Restrictions.eq("sponsoror.id", sponsorship
					.getSponsoror().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (sponsorship.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (sponsorship.getSponsoror() != null) {
			sponsororAction.setInstance(getInstance().getSponsoror());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
