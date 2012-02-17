package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Location;

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

import com.pcas.datapkg.domain.inventory.Machine;

public abstract class LocationActionBase extends BaseAction<Location>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Location location;

	@In(create = true, value = "machineAction")
	com.pcas.datapkg.web.action.inventory.MachineAction machinesAction;

	@DataModel
	private List<Location> locationRecordList;

	public void setLocationId(Long id) {
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
	public void setLocationIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getLocationId() {
		return (Long) getId();
	}

	public Location getEntity() {
		return location;
	}

	//@Override
	public void setEntity(Location t) {
		this.location = t;
		loadAssociations();
	}

	public Location getLocation() {
		return (Location) getInstance();
	}

	@Override
	protected Location createInstance() {
		Location instance = super.createInstance();

		return instance;
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

	public Location getDefinedInstance() {
		return (Location) (isIdDefined() ? getInstance() : null);
	}

	public void setLocation(Location t) {
		this.location = t;
		if (location != null)
			setLocationId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Location> getEntityClass() {
		return Location.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListMachines();

	}

	public void updateAssociations() {

		com.pcas.datapkg.domain.inventory.Machine machines = (com.pcas.datapkg.domain.inventory.Machine) org.jboss.seam.Component
				.getInstance("machine");
		machines.setLocation(location);
		events.raiseTransactionSuccessEvent("archivedMachine");

	}

	protected List<com.pcas.datapkg.domain.inventory.Machine> listMachines = new ArrayList<com.pcas.datapkg.domain.inventory.Machine>();

	void initListMachines() {

		if (listMachines.isEmpty())
			listMachines.addAll(getInstance().getMachines());

	}

	public List<com.pcas.datapkg.domain.inventory.Machine> getListMachines() {

		prePopulateListMachines();
		return listMachines;
	}

	public void prePopulateListMachines() {
	}

	public void setListMachines(
			List<com.pcas.datapkg.domain.inventory.Machine> listMachines) {
		this.listMachines = listMachines;
	}

	public void deleteMachines(int index) {
		listMachines.remove(index);
	}

	@Begin(join = true)
	public void addMachines() {
		initListMachines();
		Machine machines = new Machine();

		machines.setLocation(getInstance());

		getListMachines().add(machines);
	}

	public void updateComposedAssociations() {

		if (listMachines != null) {
			getInstance().getMachines().clear();
			getInstance().getMachines().addAll(listMachines);
		}
	}

	public void clearLists() {
		listMachines.clear();

	}

	public String viewLocation() {
		load(currentEntityId);
		return "viewLocation";
	}

}
