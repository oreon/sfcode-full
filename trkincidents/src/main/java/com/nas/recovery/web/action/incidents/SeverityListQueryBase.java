package com.nas.recovery.web.action.incidents;

import com.oreon.trkincidents.incidents.Severity;

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

import com.oreon.trkincidents.incidents.Severity;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class SeverityListQueryBase extends BaseQuery<Severity, Long> {

	private static final String EJBQL = "select severity from Severity severity";

	protected Severity severity = new Severity();

	public Severity getSeverity() {
		return severity;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Severity> getEntityClass() {
		return Severity.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"severity.id = #{severityList.severity.id}",

			"lower(severity.name) like concat(lower(#{severityList.severity.name}),'%')",

			"severity.dateCreated <= #{severityList.dateCreatedRange.end}",
			"severity.dateCreated >= #{severityList.dateCreatedRange.begin}",};

	@Observer("archivedSeverity")
	public void onArchive() {
		refresh();
	}

}
