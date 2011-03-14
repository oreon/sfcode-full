package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.Hostel;

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

import com.oreon.smartsis.hostel.Room;

public abstract class HostelActionBase extends BaseAction<Hostel>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Hostel hostel;

	@DataModel
	private List<Hostel> hostelRecordList;

	public void setHostelId(Long id) {
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
	public void setHostelIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getHostelId() {
		return (Long) getId();
	}

	public Hostel getEntity() {
		return hostel;
	}

	//@Override
	public void setEntity(Hostel t) {
		this.hostel = t;
		loadAssociations();
	}

	public Hostel getHostel() {
		return (Hostel) getInstance();
	}

	@Override
	protected Hostel createInstance() {
		return new Hostel();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Hostel getDefinedInstance() {
		return (Hostel) (isIdDefined() ? getInstance() : null);
	}

	public void setHostel(Hostel t) {
		this.hostel = t;
		loadAssociations();
	}

	@Override
	public Class<Hostel> getEntityClass() {
		return Hostel.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListRooms();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.hostel.Room> listRooms = new ArrayList<com.oreon.smartsis.hostel.Room>();

	void initListRooms() {

		if (listRooms.isEmpty())
			listRooms.addAll(getInstance().getRooms());

	}

	public List<com.oreon.smartsis.hostel.Room> getListRooms() {

		prePopulateListRooms();
		return listRooms;
	}

	public void prePopulateListRooms() {
	}

	public void setListRooms(List<com.oreon.smartsis.hostel.Room> listRooms) {
		this.listRooms = listRooms;
	}

	public void deleteRooms(int index) {
		listRooms.remove(index);
	}

	@Begin(join = true)
	public void addRooms() {
		initListRooms();
		Room rooms = new Room();

		rooms.setHostel(getInstance());

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

}
