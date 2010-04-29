package com.pge.propel.cc.action;

import com.pge.propel.cc.Component;

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

import com.pge.propel.cc.Version;

public class ComponentActionBase extends BaseAction<Component>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Component component;

	@DataModel
	private List<Component> componentList;

	@Factory("componentList")
	@Observer("archivedComponent")
	public void findRecords() {
		search();
	}

	public Component getEntity() {
		return component;
	}

	@Override
	public void setEntity(Component t) {
		this.component = t;
	}

	@Override
	public void setEntityList(List<Component> list) {
		this.componentList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (component.getProject() != null) {
			criteria = criteria.add(Restrictions.eq("project.id", component
					.getProject().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Version> listVersions;

	void initListVersions() {
		listVersions = new ArrayList<Version>();
		if (component.getVersions().isEmpty()) {

		} else
			listVersions.addAll(component.getVersions());
	}

	public List<Version> getListVersions() {
		if (listVersions == null) {
			initListVersions();
		}
		return listVersions;
	}

	public void setListVersions(List<Version> listVersions) {
		this.listVersions = listVersions;
	}

	public void deleteVersions(Version versions) {
		listVersions.remove(versions);
	}

	@Begin(join = true)
	public void addVersions() {
		Version versions = new Version();

		versions.setComponent(component);

		listVersions.add(versions);
	}

	public void updateComposedAssociations() {

		component.getVersions().clear();
		component.getVersions().addAll(listVersions);

	}

	public List<Component> getEntityList() {
		if (componentList == null) {
			findRecords();
		}
		return componentList;
	}

}
