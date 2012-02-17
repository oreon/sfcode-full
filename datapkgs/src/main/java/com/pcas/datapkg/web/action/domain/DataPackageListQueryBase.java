package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.DataPackage;

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

import com.pcas.datapkg.domain.DataPackage;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DataPackageListQueryBase
		extends
			BaseQuery<DataPackage, Long> {

	private static final String EJBQL = "select dataPackage from DataPackage dataPackage";

	protected DataPackage dataPackage = new DataPackage();

	public DataPackage getDataPackage() {
		return dataPackage;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DataPackage> getEntityClass() {
		return DataPackage.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dataPackage.id = #{dataPackageList.dataPackage.id}",

			"lower(dataPackage.name) like concat(lower(#{dataPackageList.dataPackage.name}),'%')",

			"dataPackage.dateCreated <= #{dataPackageList.dateCreatedRange.end}",
			"dataPackage.dateCreated >= #{dataPackageList.dateCreatedRange.begin}",};

	@Observer("archivedDataPackage")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DataPackage e) {

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

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
