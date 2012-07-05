package com.oreon.talent.web.action.candidates;

import com.oreon.talent.candidates.Candidate;

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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public abstract class CandidateActionBase
		extends
			com.oreon.talent.web.action.domain.PersonAction<Candidate>
		implements
			java.io.Serializable {

	@Inject
	com.oreon.talent.web.action.users.AppUserAction appUserAction;

	protected Predicate[] getSearchPredicates(Root<Candidate> root) {

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
	protected Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	public Candidate createInstance() {
		return new Candidate();
	}

	public Candidate getCandidate() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setCandidate(Candidate candidate) {
		this.entity = candidate;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public String downloadResumeFile(Long id) {
		//if(id == null || id == 0)
		//   id = currentEntityId;
		setId(id);
		downloadAttachment(getEntity().getResumeFile());
		return "success";
	}

	public void resumeFileUploadListener(FileUploadEvent event)
			throws Exception {
		UploadedFile uploadItem = event.getFile();
		if (getEntity().getResumeFile() == null)
			getEntity().setResumeFile(new FileAttachment());
		getEntity().getResumeFile().setName(uploadItem.getFileName());
		getEntity().getResumeFile().setContentType(uploadItem.getContentType());
		getEntity().getResumeFile().setData(uploadItem.getContents());
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
	public String register() {

		return null;

	}

	/** 
	 * []
	 */
	public String login() {

		return null;

	}

	/** 
	 * []
	 */
	public String retrieveCredentials() {

		return null;

	}

}
