package org.cerebrum.domain.facility.action;

import org.cerebrum.domain.facility.Bed;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("bedAction")
public class BedAction extends BaseAction<Bed> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Bed bed;

	@DataModel
	private List<Bed> bedList;

	@Factory("bedList")
	@Observer("archivedBed")
	public void findRecords() {
		search();
	}

	public Bed getEntity() {
		return bed;
	}

	@Override
	public void setEntity(Bed t) {
		this.bed = t;
	}

	@Override
	public void setEntityList(List<Bed> list) {
		this.bedList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (bed.getWard() != null) {
			criteria = criteria.add(Restrictions.eq("ward.id", bed.getWard()
					.getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Bed> getEntityList() {
		if (bedList == null) {
			findRecords();
		}
		return bedList;
	}

}
