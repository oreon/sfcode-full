package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.Hostel;

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

import com.oreon.smartsis.hostel.Hostel;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class HostelListQueryBase extends BaseQuery<Hostel, Long> {

	private static final String EJBQL = "select hostel from Hostel hostel";

	protected Hostel hostel = new Hostel();

	public Hostel getHostel() {
		return hostel;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Hostel> getEntityClass() {
		return Hostel.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"hostel.id = #{hostelList.hostel.id}",

			"lower(hostel.name) like concat(lower(#{hostelList.hostel.name}),'%')",

			"hostel.gender = #{hostelList.hostel.gender}",

			"hostel.dateCreated <= #{hostelList.dateCreatedRange.end}",
			"hostel.dateCreated >= #{hostelList.dateCreatedRange.begin}",};

	@Observer("archivedHostel")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Hostel e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Gender" + ",");

		builder.append("\r\n");
	}
}
