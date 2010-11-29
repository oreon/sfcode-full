package com.nas.recovery.web.action.facility;

import com.oreon.callosum.facility.Bed;

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

import com.oreon.callosum.facility.Bed;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class BedListQueryBase extends BaseQuery<Bed, Long> {

	private static final String EJBQL = "select bed from Bed bed";

	protected Bed bed = new Bed();

	public Bed getBed() {
		return bed;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Bed> getEntityClass() {
		return Bed.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {"bed.id = #{bedList.bed.id}",

	"bed.room.id = #{bedList.bed.room.id}",

	"lower(bed.name) like concat(lower(#{bedList.bed.name}),'%')",

	"bed.admission.id = #{bedList.bed.admission.id}",

	"bed.dateCreated <= #{bedList.dateCreatedRange.end}",
			"bed.dateCreated >= #{bedList.dateCreatedRange.begin}",};

	@Observer("archivedBed")
	public void onArchive() {
		refresh();
	}

}
