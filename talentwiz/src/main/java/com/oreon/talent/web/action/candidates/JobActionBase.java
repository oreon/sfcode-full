package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Job;

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

public abstract class JobActionBase extends BaseAction<Job>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Job job;

	@In(create = true, value = "clientAction")
	com.oreon.talent.web.action.candidates.ClientAction clientAction;

	@DataModel
	private List<Job> jobRecordList;

	public void setJobId(Long id) {
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
	public void setJobIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setClientId(Long id) {

		if (id != null && id > 0)
			getInstance().setClient(clientAction.loadFromId(id));

	}

	public Long getClientId() {
		if (getInstance().getClient() != null)
			return getInstance().getClient().getId();
		return 0L;
	}

	public Long getJobId() {
		return (Long) getId();
	}

	public Job getEntity() {
		return job;
	}

	//@Override
	public void setEntity(Job t) {
		this.job = t;
		loadAssociations();
	}

	public Job getJob() {
		return (Job) getInstance();
	}

	@Override
	protected Job createInstance() {
		Job instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.talent.candidates.Client client = clientAction
				.getDefinedInstance();
		if (client != null && isNew()) {
			getInstance().setClient(client);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Job getDefinedInstance() {
		return (Job) (isIdDefined() ? getInstance() : null);
	}

	public void setJob(Job t) {
		this.job = t;
		if (job != null)
			setJobId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Job> getEntityClass() {
		return Job.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (job.getClient() != null) {
			criteria = criteria.add(Restrictions.eq("client.id", job
					.getClient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (job.getClient() != null) {
			clientAction.setInstance(getInstance().getClient());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewJob() {
		load(currentEntityId);
		return "viewJob";
	}

}
