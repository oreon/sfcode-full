package com.pge.propel.cc.action;

import com.pge.propel.cc.solution;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import com.pge.propel.cc.Project;

public class solutionActionBase extends BaseAction<solution>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private solution solution;

	@DataModel
	private List<solution> solutionList;

	@Factory("solutionList")
	@Observer("archivedsolution")
	public void findRecords() {
		search();
	}

	public solution getEntity() {
		return solution;
	}

	@Override
	public void setEntity(solution t) {
		this.solution = t;
	}

	@Override
	public void setEntityList(List<solution> list) {
		this.solutionList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (solution.getCompany() != null) {
			criteria = criteria.add(Restrictions.eq("company.id", solution
					.getCompany().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Project> listProjects;

	void initListProjects() {
		listProjects = new ArrayList<Project>();
		if (solution.getProjects().isEmpty()) {

		} else
			listProjects.addAll(solution.getProjects());
	}

	public List<Project> getListProjects() {
		if (listProjects == null) {
			initListProjects();
		}
		return listProjects;
	}

	public void setListProjects(List<Project> listProjects) {
		this.listProjects = listProjects;
	}

	public void deleteProjects(Project projects) {
		listProjects.remove(projects);
	}

	@Begin(join = true)
	public void addProjects() {
		Project projects = new Project();

		projects.setSolution(solution);

		listProjects.add(projects);
	}

	public void updateComposedAssociations() {

		solution.getProjects().clear();
		solution.getProjects().addAll(listProjects);

	}

	public List<solution> getEntityList() {
		if (solutionList == null) {
			findRecords();
		}
		return solutionList;
	}

}
