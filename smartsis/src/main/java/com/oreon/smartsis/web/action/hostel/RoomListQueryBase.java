package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.Room;

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

import com.oreon.smartsis.hostel.Room;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoomListQueryBase extends BaseQuery<Room, Long> {

	private static final String EJBQL = "select room from Room room";

	protected Room room = new Room();

	public Room getRoom() {
		return room;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Room> getEntityClass() {
		return Room.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> chargesRange = new Range<Double>();
	public Range<Double> getChargesRange() {
		return chargesRange;
	}
	public void setCharges(Range<Double> chargesRange) {
		this.chargesRange = chargesRange;
	}

	private static final String[] RESTRICTIONS = {
			"room.id = #{roomList.room.id}",

			"lower(room.name) like concat(lower(#{roomList.room.name}),'%')",

			"room.hostel.id = #{roomList.room.hostel.id}",

			"room.charges >= #{roomList.chargesRange.begin}",
			"room.charges <= #{roomList.chargesRange.end}",

			"room.dateCreated <= #{roomList.dateCreatedRange.end}",
			"room.dateCreated >= #{roomList.dateCreatedRange.begin}",};

	public List<Room> getRoomsByHostel(com.oreon.smartsis.hostel.Hostel hostel) {
		//setMaxResults(10000);
		room.setHostel(hostel);
		return getResultList();
	}

	@Observer("archivedRoom")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Room e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getHostel() != null ? e.getHostel().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getCharges() != null ? e.getCharges() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Hostel" + ",");

		builder.append("Charges" + ",");

		builder.append("\r\n");
	}
}
