package com.hrb.tservices.web.action.offices;

import com.hrb.tservices.domain.offices.Office;

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

import com.hrb.tservices.domain.offices.Office;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OfficeListQueryBase extends BaseQuery<Office, Long> {

	private static final String EJBQL = "select office from Office office";

	protected Office office = new Office();

	public Office getOffice() {
		return office;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Office> getEntityClass() {
		return Office.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"office.id = #{officeList.office.id}",

			"lower(office.officeId) like concat(lower(#{officeList.office.officeId}),'%')",

			"lower(office.headingEN) like concat(lower(#{officeList.office.headingEN}),'%')",

			"lower(office.headingFR) like concat(lower(#{officeList.office.headingFR}),'%')",

			"lower(office.address) like concat(lower(#{officeList.office.address}),'%')",

			"lower(office.city) like concat(lower(#{officeList.office.city}),'%')",

			"lower(office.province) like concat(lower(#{officeList.office.province}),'%')",

			"lower(office.postalCode) like concat(lower(#{officeList.office.postalCode}),'%')",

			"lower(office.latitude) like concat(lower(#{officeList.office.latitude}),'%')",

			"lower(office.longitude) like concat(lower(#{officeList.office.longitude}),'%')",

			"lower(office.phone) like concat(lower(#{officeList.office.phone}),'%')",

			"lower(office.fax) like concat(lower(#{officeList.office.fax}),'%')",

			"lower(office.enInfo) like concat(lower(#{officeList.office.enInfo}),'%')",

			"lower(office.frInfo) like concat(lower(#{officeList.office.frInfo}),'%')",

			"lower(office.enHours) like concat(lower(#{officeList.office.enHours}),'%')",

			"lower(office.frHours) like concat(lower(#{officeList.office.frHours}),'%')",

			"office.dateCreated <= #{officeList.dateCreatedRange.end}",
			"office.dateCreated >= #{officeList.dateCreatedRange.begin}",};

	@Observer("archivedOffice")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Office e) {

		builder.append("\""
				+ (e.getOfficeId() != null
						? e.getOfficeId().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getHeadingEN() != null
						? e.getHeadingEN().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getHeadingFR() != null
						? e.getHeadingFR().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getAddress() != null
						? e.getAddress().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getCity() != null ? e.getCity().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getProvince() != null
						? e.getProvince().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getPostalCode() != null ? e.getPostalCode().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getLatitude() != null
						? e.getLatitude().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getLongitude() != null
						? e.getLongitude().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getPhone() != null ? e.getPhone().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getFax() != null ? e.getFax().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getEnInfo() != null ? e.getEnInfo().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getFrInfo() != null ? e.getFrInfo().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getEnHours() != null
						? e.getEnHours().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getFrHours() != null
						? e.getFrHours().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("OfficeId" + ",");

		builder.append("HeadingEN" + ",");

		builder.append("HeadingFR" + ",");

		builder.append("Address" + ",");

		builder.append("City" + ",");

		builder.append("Province" + ",");

		builder.append("PostalCode" + ",");

		builder.append("Latitude" + ",");

		builder.append("Longitude" + ",");

		builder.append("Phone" + ",");

		builder.append("Fax" + ",");

		builder.append("EnInfo" + ",");

		builder.append("FrInfo" + ",");

		builder.append("EnHours" + ",");

		builder.append("FrHours" + ",");

		builder.append("\r\n");
	}
}
