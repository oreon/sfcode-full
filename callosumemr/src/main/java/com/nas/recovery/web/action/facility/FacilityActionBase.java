package com.nas.recovery.web.action.facility;

import com.oreon.callosum.facility.Facility;

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

public abstract class FacilityActionBase extends BaseAction<Facility>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Facility facility;

	@In(create = true, value = "roomAction")
	com.nas.recovery.web.action.facility.RoomAction roomAction;

	@DataModel
	private List<Facility> facilityRecordList;

	public void setFacilityId(Long id) {
		if (id == 0) {
			clearInstance();
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
	public void setFacilityIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setRoomId(Long id) {

		if (id != null && id > 0)
			getInstance().setRoom(roomAction.loadFromId(id));

	}

	public Long getRoomId() {
		if (getInstance().getRoom() != null)
			return getInstance().getRoom().getId();
		return 0L;
	}

	public Long getFacilityId() {
		return (Long) getId();
	}

	public Facility getEntity() {
		return facility;
	}

	//@Override
	public void setEntity(Facility t) {
		this.facility = t;
		loadAssociations();
	}

	public Facility getFacility() {
		return (Facility) getInstance();
	}

	@Override
	protected Facility createInstance() {
		return new Facility();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.callosum.facility.Room room = roomAction.getDefinedInstance();
		if (room != null) {
			getInstance().setRoom(room);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Facility getDefinedInstance() {
		return (Facility) (isIdDefined() ? getInstance() : null);
	}

	public void setFacility(Facility t) {
		this.facility = t;
		loadAssociations();
	}

	@Override
	public Class<Facility> getEntityClass() {
		return Facility.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (facility.getRoom() != null) {
			criteria = criteria.add(Restrictions.eq("room.id", facility
					.getRoom().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (facility.getRoom() != null) {
			roomAction.setInstance(getInstance().getRoom());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

}
