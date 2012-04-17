package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Room;

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

import com.oreon.cerebrum.facility.Room;

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

	private static final String[] RESTRICTIONS = {
			"room.id = #{roomList.room.id}",

			"lower(room.name) like concat(lower(#{roomList.room.name}),'%')",

			"room.roomType.id = #{roomList.room.roomType.id}",

			"room.ward.id = #{roomList.room.ward.id}",

			"room.dateCreated <= #{roomList.dateCreatedRange.end}",
			"room.dateCreated >= #{roomList.dateCreatedRange.begin}",};

	public List<Room> getRoomsByWard(com.oreon.cerebrum.facility.Ward ward) {
		//setMaxResults(10000);
		room.setWard(ward);
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
				+ (e.getRoomType() != null ? e.getRoomType().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getWard() != null ? e.getWard().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("RoomType" + ",");

		builder.append("Ward" + ",");

		builder.append("\r\n");
	}
}
