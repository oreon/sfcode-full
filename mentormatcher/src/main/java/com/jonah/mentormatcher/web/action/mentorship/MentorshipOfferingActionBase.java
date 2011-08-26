package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.MentorshipOffering;

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

public abstract class MentorshipOfferingActionBase
		extends
			BaseAction<MentorshipOffering> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MentorshipOffering mentorshipOffering;

	@In(create = true, value = "categoryAction")
	com.jonah.mentormatcher.web.action.mentorship.CategoryAction categoryAction;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction mentorAction;

	@DataModel
	private List<MentorshipOffering> mentorshipOfferingRecordList;

	public void setMentorshipOfferingId(Long id) {
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
	public void setMentorshipOfferingIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setCategory(categoryAction.loadFromId(id));

	}

	public Long getCategoryId() {
		if (getInstance().getCategory() != null)
			return getInstance().getCategory().getId();
		return 0L;
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

	public Long getMentorshipOfferingId() {
		return (Long) getId();
	}

	public MentorshipOffering getEntity() {
		return mentorshipOffering;
	}

	//@Override
	public void setEntity(MentorshipOffering t) {
		this.mentorshipOffering = t;
		loadAssociations();
	}

	public MentorshipOffering getMentorshipOffering() {
		return (MentorshipOffering) getInstance();
	}

	@Override
	protected MentorshipOffering createInstance() {
		return new MentorshipOffering();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.jonah.mentormatcher.domain.mentorship.Category category = categoryAction
				.getDefinedInstance();
		if (category != null && isNew()) {
			getInstance().setCategory(category);
		}

		com.jonah.mentormatcher.domain.Employee mentor = mentorAction
				.getDefinedInstance();
		if (mentor != null && isNew()) {
			getInstance().setMentor(mentor);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MentorshipOffering getDefinedInstance() {
		return (MentorshipOffering) (isIdDefined() ? getInstance() : null);
	}

	public void setMentorshipOffering(MentorshipOffering t) {
		this.mentorshipOffering = t;
		if (mentorshipOffering != null)
			setMentorshipOfferingId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MentorshipOffering> getEntityClass() {
		return MentorshipOffering.class;
	}

	public com.jonah.mentormatcher.domain.mentorship.MentorshipOffering findByUnqTitle(
			String title) {
		return executeSingleResultNamedQuery(
				"mentorshipOffering.findByUnqTitle", title);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (mentorshipOffering.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id",
					mentorshipOffering.getCategory().getId()));
		}

		if (mentorshipOffering.getMentor() != null) {
			criteria = criteria.add(Restrictions.eq("mentor.id",
					mentorshipOffering.getMentor().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (mentorshipOffering.getCategory() != null) {
			categoryAction.setInstance(getInstance().getCategory());
		}

		if (mentorshipOffering.getMentor() != null) {
			mentorAction.setInstance(getInstance().getMentor());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	/** 
	 * []
	 */
	public String createOffering() {

		return null;

	}

	public String viewMentorshipOffering() {
		load(currentEntityId);
		return "viewMentorshipOffering";
	}

}
