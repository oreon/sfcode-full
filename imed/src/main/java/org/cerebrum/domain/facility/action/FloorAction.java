package org.cerebrum.domain.facility.action;

import org.cerebrum.domain.facility.Floor;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
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

import org.cerebrum.domain.facility.Ward;

@Scope(ScopeType.CONVERSATION)
@Name("floorAction")
public class FloorAction extends BaseAction<Floor>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Floor floor;

	@DataModel
	private List<Floor> floorList;

	@Factory("floorList")
	public void findRecords() {
		floorList = entityManager.createQuery(
				"select floor from Floor floor order by floor.id desc")
				.getResultList();
	}

	public Floor getEntity() {
		return floor;
	}

	@Override
	public void setEntity(Floor t) {
		this.floor = t;
	}

	@Override
	public void setEntityList(List<Floor> list) {
		this.floorList = list;
	}

	private List<Ward> listWards;

	void initListWards() {
		listWards = new ArrayList<Ward>();
		if (floor.getWards().isEmpty()) {

		} else
			listWards.addAll(floor.getWards());
	}

	public List<Ward> getListWards() {
		if (listWards == null) {
			initListWards();
		}
		return listWards;
	}

	public void setListWards(List<Ward> listWards) {
		this.listWards = listWards;
	}

	public void deleteWards(Ward wards) {
		listWards.remove(wards);
	}

	@Begin(join = true)
	public void addWards() {
		Ward wards = new Ward();

		wards.setFloor(floor);

		listWards.add(wards);
	}

	public void updateComposedAssociations() {

		floor.getWards().clear();
		floor.getWards().addAll(listWards);

	}

}
