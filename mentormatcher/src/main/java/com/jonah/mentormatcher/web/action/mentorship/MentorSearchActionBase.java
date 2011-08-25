package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.MentorSearch;

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

public abstract class MentorSearchActionBase extends BaseAction<MentorSearch>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MentorSearch mentorSearch;

	@In(create = true, value = "categoryAction")
	com.jonah.mentormatcher.web.action.mentorship.CategoryAction categoryAction;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction employeeAction;

	@DataModel
	private List<MentorSearch> mentorSearchRecordList;

	public void setMentorSearchId(Long id) {
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
	public void setMentorSearchIdForModalDlg(Long id) {
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

	public void setEmployeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setEmployee(employeeAction.loadFromId(id));

	}

	public Long getEmployeeId() {
		if (getInstance().getEmployee() != null)
			return getInstance().getEmployee().getId();
		return 0L;
	}

	public Long getMentorSearchId() {
		return (Long) getId();
	}

	public MentorSearch getEntity() {
		return mentorSearch;
	}

	//@Override
	public void setEntity(MentorSearch t) {
		this.mentorSearch = t;
		loadAssociations();
	}

	public MentorSearch getMentorSearch() {
		return (MentorSearch) getInstance();
	}

	@Override
	protected MentorSearch createInstance() {
		return new MentorSearch();
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

		com.jonah.mentormatcher.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null && isNew()) {
			getInstance().setEmployee(employee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MentorSearch getDefinedInstance() {
		return (MentorSearch) (isIdDefined() ? getInstance() : null);
	}

	public void setMentorSearch(MentorSearch t) {
		this.mentorSearch = t;
		if (mentorSearch != null)
			setMentorSearchId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MentorSearch> getEntityClass() {
		return MentorSearch.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (mentorSearch.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id", mentorSearch
					.getCategory().getId()));
		}

		if (mentorSearch.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id", mentorSearch
					.getEmployee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (mentorSearch.getCategory() != null) {
			categoryAction.setInstance(getInstance().getCategory());
		}

		if (mentorSearch.getEmployee() != null) {
			employeeAction.setInstance(getInstance().getEmployee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
