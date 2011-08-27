package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.Mentorship;

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

import com.jonah.mentormatcher.domain.mentorship.MentorshipMember;

public abstract class MentorshipActionBase extends BaseAction<Mentorship>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Mentorship mentorship;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction mentorAction;

	@In(create = true, value = "mentorshipMemberAction")
	com.jonah.mentormatcher.web.action.mentorship.MentorshipMemberAction menteesAction;

	@DataModel
	private List<Mentorship> mentorshipRecordList;

	public void setMentorshipId(Long id) {
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
	public void setMentorshipIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMentorId(Long id) {

		if (id != null && id > 0)
			getInstance().setMentor(mentorAction.loadFromId(id));

	}

	public Long getMentorId() {
		if (getInstance().getMentor() != null)
			return getInstance().getMentor().getId();
		return 0L;
	}

	public Long getMentorshipId() {
		return (Long) getId();
	}

	public Mentorship getEntity() {
		return mentorship;
	}

	//@Override
	public void setEntity(Mentorship t) {
		this.mentorship = t;
		loadAssociations();
	}

	public Mentorship getMentorship() {
		return (Mentorship) getInstance();
	}

	@Override
	protected Mentorship createInstance() {
		Mentorship instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.jonah.mentormatcher.domain.Employee mentor = mentorAction
				.getDefinedInstance();
		if (mentor != null && isNew()) {
			getInstance().setMentor(mentor);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Mentorship getDefinedInstance() {
		return (Mentorship) (isIdDefined() ? getInstance() : null);
	}

	public void setMentorship(Mentorship t) {
		this.mentorship = t;
		if (mentorship != null)
			setMentorshipId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Mentorship> getEntityClass() {
		return Mentorship.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (mentorship.getMentor() != null) {
			criteria = criteria.add(Restrictions.eq("mentor.id", mentorship
					.getMentor().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (mentorship.getMentor() != null) {
			mentorAction.setInstance(getInstance().getMentor());
		}

		initListMentees();

	}

	public void updateAssociations() {

		com.jonah.mentormatcher.domain.mentorship.MentorshipMember mentees = (com.jonah.mentormatcher.domain.mentorship.MentorshipMember) org.jboss.seam.Component
				.getInstance("mentorshipMember");
		mentees.setMentorship(mentorship);
		events.raiseTransactionSuccessEvent("archivedMentorshipMember");

	}

	protected List<com.jonah.mentormatcher.domain.mentorship.MentorshipMember> listMentees = new ArrayList<com.jonah.mentormatcher.domain.mentorship.MentorshipMember>();

	void initListMentees() {

		if (listMentees.isEmpty())
			listMentees.addAll(getInstance().getMentees());

	}

	public List<com.jonah.mentormatcher.domain.mentorship.MentorshipMember> getListMentees() {

		prePopulateListMentees();
		return listMentees;
	}

	public void prePopulateListMentees() {
	}

	public void setListMentees(
			List<com.jonah.mentormatcher.domain.mentorship.MentorshipMember> listMentees) {
		this.listMentees = listMentees;
	}

	public void deleteMentees(int index) {
		listMentees.remove(index);
	}

	@Begin(join = true)
	public void addMentees() {
		initListMentees();
		MentorshipMember mentees = new MentorshipMember();

		mentees.setMentorship(getInstance());

		getListMentees().add(mentees);
	}

	public void updateComposedAssociations() {

		if (listMentees != null) {
			getInstance().getMentees().clear();
			getInstance().getMentees().addAll(listMentees);
		}
	}

	public void clearLists() {
		listMentees.clear();

	}

	public String viewMentorship() {
		load(currentEntityId);
		return "viewMentorship";
	}

}
