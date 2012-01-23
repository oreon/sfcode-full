package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.JobApplication;

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

public abstract class JobApplicationActionBase
		extends
			BaseAction<JobApplication> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private JobApplication jobApplication;

	@In(create = true, value = "candidateAction")
	com.oreon.talent.web.action.candidates.CandidateAction candidateAction;

	@In(create = true, value = "jobAction")
	com.oreon.talent.web.action.candidates.JobAction jobAction;

	@DataModel
	private List<JobApplication> jobApplicationRecordList;

	public void setJobApplicationId(Long id) {
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
	public void setJobApplicationIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setCandidateId(Long id) {

		if (id != null && id > 0)
			getInstance().setCandidate(candidateAction.loadFromId(id));

	}

	public Long getCandidateId() {
		if (getInstance().getCandidate() != null)
			return getInstance().getCandidate().getId();
		return 0L;
	}

	public void setJobId(Long id) {

		if (id != null && id > 0)
			getInstance().setJob(jobAction.loadFromId(id));

	}

	public Long getJobId() {
		if (getInstance().getJob() != null)
			return getInstance().getJob().getId();
		return 0L;
	}

	public Long getJobApplicationId() {
		return (Long) getId();
	}

	public JobApplication getEntity() {
		return jobApplication;
	}

	//@Override
	public void setEntity(JobApplication t) {
		this.jobApplication = t;
		loadAssociations();
	}

	public JobApplication getJobApplication() {
		return (JobApplication) getInstance();
	}

	@Override
	protected JobApplication createInstance() {
		JobApplication instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.talent.candidates.Candidate candidate = candidateAction
				.getDefinedInstance();
		if (candidate != null && isNew()) {
			getInstance().setCandidate(candidate);
		}

		com.oreon.talent.candidates.Job job = jobAction.getDefinedInstance();
		if (job != null && isNew()) {
			getInstance().setJob(job);
		}

	}

	public boolean isWired() {
		return true;
	}

	public JobApplication getDefinedInstance() {
		return (JobApplication) (isIdDefined() ? getInstance() : null);
	}

	public void setJobApplication(JobApplication t) {
		this.jobApplication = t;
		if (jobApplication != null)
			setJobApplicationId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<JobApplication> getEntityClass() {
		return JobApplication.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (jobApplication.getCandidate() != null) {
			criteria = criteria.add(Restrictions.eq("candidate.id",
					jobApplication.getCandidate().getId()));
		}

		if (jobApplication.getJob() != null) {
			criteria = criteria.add(Restrictions.eq("job.id", jobApplication
					.getJob().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (jobApplication.getCandidate() != null) {
			candidateAction.setInstance(getInstance().getCandidate());
		}

		if (jobApplication.getJob() != null) {
			jobAction.setInstance(getInstance().getJob());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewJobApplication() {
		load(currentEntityId);
		return "viewJobApplication";
	}

}
