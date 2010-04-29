package com.pge.propel.cc.action;

import com.pge.propel.cc.Version;

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

public class VersionActionBase extends BaseAction<Version>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Version version;

	@DataModel
	private List<Version> versionList;

	@Factory("versionList")
	@Observer("archivedVersion")
	public void findRecords() {
		search();
	}

	public Version getEntity() {
		return version;
	}

	@Override
	public void setEntity(Version t) {
		this.version = t;
	}

	@Override
	public void setEntityList(List<Version> list) {
		this.versionList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (version.getComponent() != null) {
			criteria = criteria.add(Restrictions.eq("component.id", version
					.getComponent().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Version> getEntityList() {
		if (versionList == null) {
			findRecords();
		}
		return versionList;
	}

}
