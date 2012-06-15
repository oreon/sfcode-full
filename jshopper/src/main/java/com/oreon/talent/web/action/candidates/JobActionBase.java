package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Job;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DualListModel;

import org.witchcraft.utils.ViewUtils;
import javax.inject.Inject;

import com.oreon.talent.candidates.JobApplication;

public abstract class JobActionBase extends BaseAction<Job>
		implements
			java.io.Serializable {

	@Inject
	com.oreon.talent.web.action.candidates.ClientAction clientAction;

	protected Predicate[] getSearchPredicates(Root<Job> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<Job> getEntityClass() {
		return Job.class;
	}

	public Job createInstance() {
		return new Job();
	}

	public Job getJob() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setJob(Job job) {
		this.entity = job;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListJobApplications();

	}

	public void updateAssociations() {
	}

	protected List<com.oreon.talent.candidates.JobApplication> listJobApplications = new ArrayList<com.oreon.talent.candidates.JobApplication>();

	void initListJobApplications() {
		prePopulateListJobApplications();
		listJobApplications.addAll(getEntity().getJobApplications());
	}

	public List<com.oreon.talent.candidates.JobApplication> getListJobApplications() {
		return listJobApplications;
	}

	public void setListJobApplications(
			List<com.oreon.talent.candidates.JobApplication> listJobApplications) {
		this.listJobApplications = listJobApplications;
	}

	public void prePopulateListJobApplications() {
	}

	public void deleteJobApplications(int index) {
		listJobApplications.remove(index);
	}

	public void addJobApplications() {
		initListJobApplications();
		JobApplication jobApplications = new JobApplication();

		jobApplications.setJob(getEntity());

		getListJobApplications().add(jobApplications);
	}

	public void updateComposedAssociations() {

		if (listJobApplications != null) {
			getEntity().getJobApplications().clear();
			getEntity().getJobApplications().addAll(listJobApplications);
		}
	}

	public void clearLists() {
		listJobApplications.clear();

	}

	/** 
	 * []
	 */
	public void apply() {

	}

}
