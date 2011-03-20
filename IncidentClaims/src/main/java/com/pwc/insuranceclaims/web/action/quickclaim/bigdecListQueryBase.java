package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.bigdec;

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

import com.pwc.insuranceclaims.quickclaim.bigdec;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class bigdecListQueryBase extends BaseQuery<bigdec, Long> {

	private static final String EJBQL = "select bigdec from bigdec bigdec";

	protected bigdec bigdec = new bigdec();

	public bigdec getbigdec() {
		return bigdec;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<bigdec> getEntityClass() {
		return bigdec.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"bigdec.id = #{bigdecList.bigdec.id}",

			"bigdec.dateCreated <= #{bigdecList.dateCreatedRange.end}",
			"bigdec.dateCreated >= #{bigdecList.dateCreatedRange.begin}",};

	@Observer("archivedbigdec")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, bigdec e) {

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
