package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Bed;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.facility.Bed;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedListQueryBase extends BaseQuery<Bed, Long> {

	private static final String EJBQL = "select bed from Bed bed";

	protected Bed bed = new Bed();

	public BedListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Bed getBed() {
		return bed;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Bed getInstance() {
		return getBed();
	}

	@Override
	//@Restrict("#{s:hasPermission('bed', 'view')}")
	public List<Bed> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Bed> getEntityClass() {
		return Bed.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {"bed.id = #{bedList.bed.id}",

	"bed.archived = #{bedList.bed.archived}",

	"bed.room.id = #{bedList.bed.room.id}",

	"lower(bed.name) like concat(lower(#{bedList.bed.name}),'%')",

	"bed.patient.id = #{bedList.bed.patient.id}",

	"bed.dateCreated <= #{bedList.dateCreatedRange.end}",
			"bed.dateCreated >= #{bedList.dateCreatedRange.begin}",};

	/** 
	 * List of all Beds for the given Room
	 * @param patient
	 * @return 
	 */
	public List<Bed> getAllBedsByRoom(
			final com.oreon.cerebrum.facility.Room room) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		bed.setRoom(room);
		return getResultListTable();
	}

	public LazyDataModel<Bed> getBedsByRoom(
			final com.oreon.cerebrum.facility.Room room) {

		EntityLazyDataModel<Bed, Long> bedLazyDataModel = new EntityLazyDataModel<Bed, Long>(
				this) {

			@Override
			public List<Bed> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				bed.setRoom(room);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return bedLazyDataModel;

	}

	/** 
	 * List of all Beds for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Bed> getAllBedByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		bed.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Bed> getBedByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Bed, Long> bedLazyDataModel = new EntityLazyDataModel<Bed, Long>(
				this) {

			@Override
			public List<Bed> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				bed.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return bedLazyDataModel;

	}

	@Observer("archivedBed")
	public void onArchive() {
		refresh();
	}

	public void setRoomId(Long id) {
		if (bed.getRoom() == null) {
			bed.setRoom(new com.oreon.cerebrum.facility.Room());
		}
		bed.getRoom().setId(id);
	}

	public Long getRoomId() {
		return bed.getRoom() == null ? null : bed.getRoom().getId();
	}

	public void setPatientId(Long id) {
		if (bed.getPatient() == null) {
			bed.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		bed.getPatient().setId(id);
	}

	public Long getPatientId() {
		return bed.getPatient() == null ? null : bed.getPatient().getId();
	}

}
