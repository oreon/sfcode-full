package com.pge.propel.cc.action;

import com.pge.propel.cc.Project;

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

import com.pge.propel.cc.Component;

public class ProjectActionBase extends BaseAction<Project>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Project project;

	@DataModel
	private List<Project> projectList;

	@Factory("projectList")
	@Observer("archivedProject")
	public void findRecords() {
		search();
	}

	public Project getEntity() {
		return project;
	}

	@Override
	public void setEntity(Project t) {
		this.project = t;
	}

	@Override
	public void setEntityList(List<Project> list) {
		this.projectList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (project.getSolution() != null) {
			criteria = criteria.add(Restrictions.eq("solution.id", project
					.getSolution().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Component> listComponents;

	void initListComponents() {
		listComponents = new ArrayList<Component>();
		if (project.getComponents().isEmpty()) {

		} else
			listComponents.addAll(project.getComponents());
	}

	public List<Component> getListComponents() {
		if (listComponents == null) {
			initListComponents();
		}
		return listComponents;
	}

	public void setListComponents(List<Component> listComponents) {
		this.listComponents = listComponents;
	}

	public void deleteComponents(Component components) {
		listComponents.remove(components);
	}

	@Begin(join = true)
	public void addComponents() {
		Component components = new Component();

		components.setProject(project);

		listComponents.add(components);
	}

	public void updateComposedAssociations() {

		project.getComponents().clear();
		project.getComponents().addAll(listComponents);

	}

	public List<Project> getEntityList() {
		if (projectList == null) {
			findRecords();
		}
		return projectList;
	}

}
