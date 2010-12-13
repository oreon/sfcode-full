package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Frequecy;

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

import com.oreon.callosum.patient.Frequecy;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class FrequecyListQueryBase extends BaseQuery<Frequecy, Long> {

	private static final String EJBQL = "select frequecy from Frequecy frequecy";

	protected Frequecy frequecy = new Frequecy();

	public Frequecy getFrequecy() {
		return frequecy;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Frequecy> getEntityClass() {
		return Frequecy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> qtyPerDayRange = new Range<Integer>();
	public Range<Integer> getQtyPerDayRange() {
		return qtyPerDayRange;
	}
	public void setQtyPerDay(Range<Integer> qtyPerDayRange) {
		this.qtyPerDayRange = qtyPerDayRange;
	}

	private static final String[] RESTRICTIONS = {
			"frequecy.id = #{frequecyList.frequecy.id}",

			"lower(frequecy.name) like concat(lower(#{frequecyList.frequecy.name}),'%')",

			"frequecy.qtyPerDay >= #{frequecyList.qtyPerDayRange.begin}",
			"frequecy.qtyPerDay <= #{frequecyList.qtyPerDayRange.end}",

			"frequecy.dateCreated <= #{frequecyList.dateCreatedRange.end}",
			"frequecy.dateCreated >= #{frequecyList.dateCreatedRange.begin}",};

	@Observer("archivedFrequecy")
	public void onArchive() {
		refresh();
	}

}
