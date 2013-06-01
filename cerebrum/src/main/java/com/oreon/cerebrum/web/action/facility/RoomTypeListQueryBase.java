package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.RoomType;

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

import com.oreon.cerebrum.facility.RoomType;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoomTypeListQueryBase extends BaseQuery<RoomType, Long> {

	private static final String EJBQL = "select roomType from RoomType roomType";

	protected RoomType roomType = new RoomType();

	public RoomType getRoomType() {
		return roomType;
	}

	@Override
	public RoomType getInstance() {
		return getRoomType();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('roomType', 'view')}")
	public List<RoomType> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<RoomType> getEntityClass() {
		return RoomType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> rateRange = new Range<Double>();

	public Range<Double> getRateRange() {
		return rateRange;
	}
	public void setRate(Range<Double> rateRange) {
		this.rateRange = rateRange;
	}

	private Range<Integer> numberOfRoomsRange = new Range<Integer>();

	public Range<Integer> getNumberOfRoomsRange() {
		return numberOfRoomsRange;
	}
	public void setNumberOfRooms(Range<Integer> numberOfRoomsRange) {
		this.numberOfRoomsRange = numberOfRoomsRange;
	}

	private static final String[] RESTRICTIONS = {
			"roomType.id = #{roomTypeList.roomType.id}",

			"roomType.archived = #{roomTypeList.roomType.archived}",

			"lower(roomType.name) like concat(lower(#{roomTypeList.roomType.name}),'%')",

			"lower(roomType.description) like concat(lower(#{roomTypeList.roomType.description}),'%')",

			"roomType.rate >= #{roomTypeList.rateRange.begin}",
			"roomType.rate <= #{roomTypeList.rateRange.end}",

			"roomType.numberOfRooms >= #{roomTypeList.numberOfRoomsRange.begin}",
			"roomType.numberOfRooms <= #{roomTypeList.numberOfRoomsRange.end}",

			"roomType.dateCreated <= #{roomTypeList.dateCreatedRange.end}",
			"roomType.dateCreated >= #{roomTypeList.dateCreatedRange.begin}",};

	@Observer("archivedRoomType")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, RoomType e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\"" + (e.getRate() != null ? e.getRate() : "") + "\",");

		builder.append("\""
				+ (e.getNumberOfRooms() != null ? e.getNumberOfRooms() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Description" + ",");

		builder.append("Rate" + ",");

		builder.append("NumberOfRooms" + ",");

		builder.append("\r\n");
	}
}
