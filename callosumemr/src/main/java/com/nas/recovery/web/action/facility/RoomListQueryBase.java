package com.nas.recovery.web.action.facility;

import com.oreon.callosum.facility.Room;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.callosum.facility.Room;

/**
 * @author WitchcraftMDA Seam Cartridge
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

			"room.facility.id = #{roomList.room.facility.id}",

			"room.bed.id = #{roomList.room.bed.id}",

			"lower(room.name) like concat(lower(#{roomList.room.name}),'%')",

			"room.dateCreated <= #{roomList.dateCreatedRange.end}",
			"room.dateCreated >= #{roomList.dateCreatedRange.begin}",};

	@Observer("archivedRoom")
	public void onArchive() {
		refresh();
	}

}
