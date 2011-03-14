package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.Bed;

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

import com.oreon.smartsis.hostel.Bed;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedListQueryBase extends BaseQuery<Bed, Long> {

	private static final String EJBQL = "select bed from Bed bed";

	protected Bed bed = new Bed();

	public Bed getBed() {
		return bed;
	}

	@Override
	protected String getql() {
		return EJBQL;
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

	"bed.room.id = #{bedList.bed.room.id}",

	"lower(bed.name) like concat(lower(#{bedList.bed.name}),'%')",

	"bed.dateCreated <= #{bedList.dateCreatedRange.end}",
			"bed.dateCreated >= #{bedList.dateCreatedRange.begin}",};

	public List<Bed> getBedsByRoom(com.oreon.smartsis.hostel.Room room) {
		//setMaxResults(10000);
		bed.setRoom(room);
		return getResultList();
	}

	@Observer("archivedBed")
	public void onArchive() {
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

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Room" + ",");

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
