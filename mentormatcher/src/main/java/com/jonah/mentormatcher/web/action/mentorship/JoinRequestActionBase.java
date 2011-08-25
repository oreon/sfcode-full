package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.JoinRequest;

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

public abstract class JoinRequestActionBase extends BaseAction<JoinRequest>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private JoinRequest joinRequest;

	@In(create = true, value = "mentorshipOfferingAction")
	com.jonah.mentormatcher.web.action.mentorship.MentorshipOfferingAction mentorshipOfferingAction;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction prospectiveMenteeAction;

	@DataModel
	private List<JoinRequest> joinRequestRecordList;

	public void setJoinRequestId(Long id) {
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
	public void setJoinRequestIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMentorshipOfferingId(Long id) {

		if (id != null && id > 0)
			getInstance().setMentorshipOffering(
					mentorshipOfferingAction.loadFromId(id));

	}

	public Long getMentorshipOfferingId() {
		if (getInstance().getMentorshipOffering() != null)
			return getInstance().getMentorshipOffering().getId();
		return 0L;
	}

	public void setProspectiveMenteeId(Long id) {

		if (id != null && id > 0)
			getInstance().setProspectiveMentee(
					prospectiveMenteeAction.loadFromId(id));

	}

	public Long getProspectiveMenteeId() {
		if (getInstance().getProspectiveMentee() != null)
			return getInstance().getProspectiveMentee().getId();
		return 0L;
	}

	public Long getJoinRequestId() {
		return (Long) getId();
	}

	public JoinRequest getEntity() {
		return joinRequest;
	}

	//@Override
	public void setEntity(JoinRequest t) {
		this.joinRequest = t;
		loadAssociations();
	}

	public JoinRequest getJoinRequest() {
		return (JoinRequest) getInstance();
	}

	@Override
	protected JoinRequest createInstance() {
		return new JoinRequest();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.jonah.mentormatcher.domain.mentorship.MentorshipOffering mentorshipOffering = mentorshipOfferingAction
				.getDefinedInstance();
		if (mentorshipOffering != null && isNew()) {
			getInstance().setMentorshipOffering(mentorshipOffering);
		}

		com.jonah.mentormatcher.domain.Employee prospectiveMentee = prospectiveMenteeAction
				.getDefinedInstance();
		if (prospectiveMentee != null && isNew()) {
			getInstance().setProspectiveMentee(prospectiveMentee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public JoinRequest getDefinedInstance() {
		return (JoinRequest) (isIdDefined() ? getInstance() : null);
	}

	public void setJoinRequest(JoinRequest t) {
		this.joinRequest = t;
		if (joinRequest != null)
			setJoinRequestId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<JoinRequest> getEntityClass() {
		return JoinRequest.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (joinRequest.getMentorshipOffering() != null) {
			criteria = criteria.add(Restrictions.eq("mentorshipOffering.id",
					joinRequest.getMentorshipOffering().getId()));
		}

		if (joinRequest.getProspectiveMentee() != null) {
			criteria = criteria.add(Restrictions.eq("prospectiveMentee.id",
					joinRequest.getProspectiveMentee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (joinRequest.getMentorshipOffering() != null) {
			mentorshipOfferingAction.setInstance(getInstance()
					.getMentorshipOffering());
		}

		if (joinRequest.getProspectiveMentee() != null) {
			prospectiveMenteeAction.setInstance(getInstance()
					.getProspectiveMentee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
