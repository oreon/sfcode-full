package com.nas.recovery.web.action.facility;

import com.oreon.callosum.facility.Room;

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

public abstract class RoomActionBase extends BaseAction<Room>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Room room;

	@In(create = true, value = "facilityAction")
	com.nas.recovery.web.action.facility.FacilityAction facilityAction;

	@In(create = true, value = "bedAction")
	com.nas.recovery.web.action.facility.BedAction bedAction;

	@DataModel
	private List<Room> roomRecordList;

	public void setRoomId(Long id) {
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
	public void setRoomIdForModalDlg(Long id) {
		setId(id);
		loadAssociations();
	}

	public void setFacilityId(Long id) {

		if (id != null && id > 0)
			getInstance().setFacility(facilityAction.loadFromId(id));

	}

	public Long getFacilityId() {
		if (getInstance().getFacility() != null)
			return getInstance().getFacility().getId();
		return 0L;
	}

	public void setBedId(Long id) {

		if (id != null && id > 0)
			getInstance().setBed(bedAction.loadFromId(id));

	}

	public Long getBedId() {
		if (getInstance().getBed() != null)
			return getInstance().getBed().getId();
		return 0L;
	}

	public Long getRoomId() {
		return (Long) getId();
	}

	public Room getEntity() {
		return room;
	}

	//@Override
	public void setEntity(Room t) {
		this.room = t;
		loadAssociations();
	}

	public Room getRoom() {
		return (Room) getInstance();
	}

	@Override
	protected Room createInstance() {
		return new Room();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.callosum.facility.Facility facility = facilityAction
				.getDefinedInstance();
		if (facility != null) {
			getInstance().setFacility(facility);
		}

		com.oreon.callosum.facility.Bed bed = bedAction.getDefinedInstance();
		if (bed != null) {
			getInstance().setBed(bed);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Room getDefinedInstance() {
		return (Room) (isIdDefined() ? getInstance() : null);
	}

	public void setRoom(Room t) {
		this.room = t;
		loadAssociations();
	}

	@Override
	public Class<Room> getEntityClass() {
		return Room.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (room.getFacility() != null) {
			criteria = criteria.add(Restrictions.eq("facility.id", room
					.getFacility().getId()));
		}

		if (room.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", room.getBed()
					.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (room.getFacility() != null) {
			facilityAction.setInstance(getInstance().getFacility());
		}

		if (room.getBed() != null) {
			bedAction.setInstance(getInstance().getBed());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

}
