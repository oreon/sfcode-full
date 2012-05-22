package com.pcas.datapkg.web.action.customReports;

import com.pcas.datapkg.customReports.MetaField;

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

import com.pcas.datapkg.customReports.MetaField;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MetaFieldListQueryBase extends BaseQuery<MetaField, Long> {

	private static final String EJBQL = "select metaField from MetaField metaField";

	protected MetaField metaField = new MetaField();

	public MetaField getMetaField() {
		return metaField;
	}

	private com.pcas.datapkg.customReports.CustomReport customReportsToSearch;

	public void setCustomReportsToSearch(
			com.pcas.datapkg.customReports.CustomReport customReportToSearch) {
		this.customReportsToSearch = customReportToSearch;
	}

	public com.pcas.datapkg.customReports.CustomReport getCustomReportsToSearch() {
		return customReportsToSearch;
	}

	private com.pcas.datapkg.customReports.CustomReport groupReportToSearch;

	public void setGroupReportToSearch(
			com.pcas.datapkg.customReports.CustomReport customReportToSearch) {
		this.groupReportToSearch = customReportToSearch;
	}

	public com.pcas.datapkg.customReports.CustomReport getGroupReportToSearch() {
		return groupReportToSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MetaField> getEntityClass() {
		return MetaField.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"metaField.id = #{metaFieldList.metaField.id}",

			"lower(metaField.name) like concat(lower(#{metaFieldList.metaField.name}),'%')",

			"#{metaFieldList.customReportsToSearch} in elements(metaField.customReports)",

			"#{metaFieldList.groupReportToSearch} in elements(metaField.groupReport)",

			"lower(metaField.type) like concat(lower(#{metaFieldList.metaField.type}),'%')",

			"metaField.metaEntity.id = #{metaFieldList.metaField.metaEntity.id}",

			"metaField.dateCreated <= #{metaFieldList.dateCreatedRange.end}",
			"metaField.dateCreated >= #{metaFieldList.dateCreatedRange.begin}",};

	public List<MetaField> getMetaFieldsByMetaEntity(
			com.pcas.datapkg.customReports.MetaEntity metaEntity) {
		//setMaxResults(10000);
		metaField.setMetaEntity(metaEntity);
		return getResultList();
	}

	@Observer("archivedMetaField")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MetaField e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getCustomReports() != null ? e.getCustomReports() : "")
				+ "\",");

		builder.append("\""
				+ (e.getGroupReport() != null ? e.getGroupReport() : "")
				+ "\",");

		builder.append("\""
				+ (e.getType() != null ? e.getType().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getMetaEntity() != null ? e.getMetaEntity()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("CustomReports" + ",");

		builder.append("GroupReport" + ",");

		builder.append("Type" + ",");

		builder.append("MetaEntity" + ",");

		builder.append("\r\n");
	}
}
