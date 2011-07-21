package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.Bed;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class BedActionBase extends BaseAction<Bed>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Bed bed;

	@In(create = true, value = "roomAction")
	com.oreon.smartsis.web.action.hostel.RoomAction roomAction;

	@DataModel
	private List<Bed> bedRecordList;

	public void setBedId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
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
	public void setBedIdForModalDlg(Long id) {
		setId(id);
		clearLists();
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

	public Long getBedId() {
		return (Long) getId();
	}

	public Bed getEntity() {
		return bed;
	}

	//@Override
	public void setEntity(Bed t) {
		this.bed = t;
		loadAssociations();
	}

	public Bed getBed() {
		return (Bed) getInstance();
	}

	@Override
	protected Bed createInstance() {
		return new Bed();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.hostel.Room room = roomAction.getDefinedInstance();
		if (room != null && isNew()) {
			getInstance().setRoom(room);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Bed getDefinedInstance() {
		return (Bed) (isIdDefined() ? getInstance() : null);
	}

	public void setBed(Bed t) {
		this.bed = t;
		if (bed != null)
			setBedId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Bed> getEntityClass() {
		return Bed.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (bed.getRoom() != null) {
			criteria = criteria.add(Restrictions.eq("room.id", bed.getRoom()
					.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (bed.getRoom() != null) {
			roomAction.setInstance(getInstance().getRoom());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public List availableBeds() {

		return executeNamedQuery("availableBeds");

	}

}
