package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Candidate;

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

public abstract class CandidateActionBase
		extends
			com.oreon.talent.web.action.domain.PersonAction<Candidate>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Candidate candidate;

	@In(create = true, value = "appUserAction")
	com.oreon.talent.web.action.users.AppUserAction appUserAction;

	@DataModel
	private List<Candidate> candidateRecordList;

	public void setCandidateId(Long id) {
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
	public void setCandidateIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
		return 0L;
	}

	public Long getCandidateId() {
		return (Long) getId();
	}

	public Candidate getEntity() {
		return candidate;
	}

	//@Override
	public void setEntity(Candidate t) {
		this.candidate = t;
		loadAssociations();
	}

	public Candidate getCandidate() {
		return (Candidate) getInstance();
	}

	@Override
	protected Candidate createInstance() {
		Candidate instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.talent.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Candidate getDefinedInstance() {
		return (Candidate) (isIdDefined() ? getInstance() : null);
	}

	public void setCandidate(Candidate t) {
		this.candidate = t;
		if (candidate != null)
			setCandidateId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	public String downloadResumeFile(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getResumeFile());
		return "success";
	}

	public void resumeFileUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getResumeFile() == null)
			getInstance().setResumeFile(new FileAttachment());
		getInstance().getResumeFile().setName(uploadItem.getFileName());
		getInstance().getResumeFile().setContentType(
				uploadItem.getContentType());
		getInstance().getResumeFile().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (candidate.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", candidate
					.getAppUser().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (candidate.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public Candidate getCurrentLoggedInCandidate() {
		String query = "Select e from Candidate e where e.appUser.userName = ?1";
		return (Candidate) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String viewCandidate() {
		load(currentEntityId);
		return "viewCandidate";
	}

}
