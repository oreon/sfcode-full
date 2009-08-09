package org.cerebrum.domain.facility.action;

import org.cerebrum.domain.facility.Ward;
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

import org.jboss.seam.Component;
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

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

import org.cerebrum.domain.facility.Bed;

public class WardActionBase extends BaseAction<Ward>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Ward ward;

	@DataModel
	private List<Ward> wardList;

	@Factory("wardList")
	@Observer("archivedWard")
	public void findRecords() {
		search();
	}

	public Ward getEntity() {
		return ward;
	}

	@Override
	public void setEntity(Ward t) {
		this.ward = t;
	}

	@Override
	public void setEntityList(List<Ward> list) {
		this.wardList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (ward.getFloor() != null) {
			criteria = criteria.add(Restrictions.eq("floor.id", ward.getFloor()
					.getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<Bed> listBeds;

	void initListBeds() {
		listBeds = new ArrayList<Bed>();
		if (ward.getBeds().isEmpty()) {

		} else
			listBeds.addAll(ward.getBeds());
	}

	public List<Bed> getListBeds() {
		if (listBeds == null) {
			initListBeds();
		}
		return listBeds;
	}

	public void setListBeds(List<Bed> listBeds) {
		this.listBeds = listBeds;
	}

	public void deleteBeds(Bed beds) {
		listBeds.remove(beds);
	}

	@Begin(join = true)
	public void addBeds() {
		Bed beds = new Bed();

		beds.setWard(ward);

		listBeds.add(beds);
	}

	public void updateComposedAssociations() {

		ward.getBeds().clear();
		ward.getBeds().addAll(listBeds);

	}

	public List<Ward> getEntityList() {
		if (wardList == null) {
			findRecords();
		}
		return wardList;
	}

}
