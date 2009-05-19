package org.cerebrum.domain.admission.action;

import org.cerebrum.domain.admission.BedAllocation;
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
@Name("bedAllocationAction")
public class BedAllocationAction extends BaseAction<BedAllocation>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private BedAllocation bedAllocation;

	@DataModel
	private List<BedAllocation> bedAllocationList;

	@Factory("bedAllocationList")
	@Observer("archivedBedAllocation")
	public void findRecords() {
		search();
	}

	public BedAllocation getEntity() {
		return bedAllocation;
	}

	@Override
	public void setEntity(BedAllocation t) {
		this.bedAllocation = t;
	}

	@Override
	public void setEntityList(List<BedAllocation> list) {
		this.bedAllocationList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (bedAllocation.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", bedAllocation
					.getBed().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<BedAllocation> getEntityList() {
		if (bedAllocationList == null) {
			findRecords();
		}
		return bedAllocationList;
	}

}
