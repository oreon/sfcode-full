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
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.facility.Bed;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedListQueryBase extends BaseQuery<Bed, Long> {

	private static final String EJBQL = "select bed from Bed bed";

	protected Bed bed = new Bed();

	@In(create = true)
	BedAction bedAction;

	public BedListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Bed getBed() {
		return bed;
	}

	@Override
	public Bed getInstance() {
		return getBed();
	}

	@Override
	protected String getql() {
		return EJBQL;
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

	public List<Bed> getBedsByRoom(com.oreon.cerebrum.facility.Room room) {
		bed.setRoom(room);
		return getResultList();
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

	//@Restrict("#{s:hasPermission('bed', 'delete')}")
	public void archiveById(Long id) {
		bedAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Bed e) {

		builder.append("\""
				+ (e.getRoom() != null ? e.getRoom().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Room" + ",");

		builder.append("Name" + ",");

		builder.append("Patient" + ",");

		builder.append("\r\n");
	}
}
