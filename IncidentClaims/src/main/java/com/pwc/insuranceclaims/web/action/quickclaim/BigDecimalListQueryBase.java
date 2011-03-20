package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.BigDecimal;

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

import com.pwc.insuranceclaims.quickclaim.BigDecimal;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BigDecimalListQueryBase
		extends
			BaseQuery<BigDecimal, Long> {

	private static final String EJBQL = "select bigDecimal from BigDecimal bigDecimal";

	protected BigDecimal bigDecimal = new BigDecimal();

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<BigDecimal> getEntityClass() {
		return BigDecimal.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"bigDecimal.id = #{bigDecimalList.bigDecimal.id}",

			"bigDecimal.dateCreated <= #{bigDecimalList.dateCreatedRange.end}",
			"bigDecimal.dateCreated >= #{bigDecimalList.dateCreatedRange.begin}",};

	@Observer("archivedBigDecimal")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, BigDecimal e) {

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("\r\n");
	}
}
