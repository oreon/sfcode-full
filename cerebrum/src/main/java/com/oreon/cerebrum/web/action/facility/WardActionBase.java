package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Ward;

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

import com.oreon.cerebrum.facility.Room;

public abstract class WardActionBase extends BaseAction<Ward>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Ward ward;

	@In(create = true, value = "facilityAction")
	com.oreon.cerebrum.web.action.facility.FacilityAction facilityAction;

	//@DataModel
	//private List<Ward> wardRecordList;	

	public void setWardId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		ward = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setWardIdForModalDlg(Long id) {
		setId(id);
		ward = loadInstance();
		clearLists();
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

	public Long getWardId() {
		return (Long) getId();
	}

	public Ward getEntity() {
		return ward;
	}

	//@Override
	public void setEntity(Ward t) {
		this.ward = t;
		loadAssociations();
	}

	public Ward getWard() {
		return (Ward) getInstance();
	}

	@Override
	protected Ward createInstance() {
		Ward instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.facility.Facility facility = facilityAction
				.getDefinedInstance();
		if (facility != null && isNew()) {
			getInstance().setFacility(facility);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Ward getDefinedInstance() {
		return (Ward) (isIdDefined() ? getInstance() : null);
	}

	public void setWard(Ward t) {
		this.ward = t;
		if (ward != null)
			setWardId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Ward> getEntityClass() {
		return Ward.class;
	}

	public com.oreon.cerebrum.facility.Ward findByUnqName(String name) {
		return executeSingleResultNamedQuery("ward.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (ward.getFacility() != null) {
			criteria = criteria.add(Restrictions.eq("facility.id", ward
					.getFacility().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (ward.getFacility() != null) {
			facilityAction.setInstance(getInstance().getFacility());
			facilityAction.loadAssociations();
		}

		initListRooms();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.facility.Room> listRooms = new ArrayList<com.oreon.cerebrum.facility.Room>();

	void initListRooms() {

		if (listRooms.isEmpty())
			listRooms.addAll(getInstance().getRooms());

	}

	public List<com.oreon.cerebrum.facility.Room> getListRooms() {

		prePopulateListRooms();
		return listRooms;
	}

	public void prePopulateListRooms() {
	}

	public void setListRooms(List<com.oreon.cerebrum.facility.Room> listRooms) {
		this.listRooms = listRooms;
	}

	public void deleteRooms(int index) {
		listRooms.remove(index);
	}

	@Begin(join = true)
	public void addRooms() {
		initListRooms();
		Room rooms = new Room();

		rooms.setWard(getInstance());

		getListRooms().add(rooms);
	}

	public void updateComposedAssociations() {

		if (listRooms != null) {
			getInstance().getRooms().clear();
			getInstance().getRooms().addAll(listRooms);
		}
	}

	public void clearLists() {
		listRooms.clear();

	}

	public String viewWard() {
		load(currentEntityId);
		return "viewWard";
	}

}
