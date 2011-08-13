package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Settings;

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

import com.oreon.smartsis.domain.Settings;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SettingsListQueryBase extends BaseQuery<Settings, Long> {

	private static final String EJBQL = "select settings from Settings settings";

	protected Settings settings = new Settings();

	public Settings getSettings() {
		return settings;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Settings> getEntityClass() {
		return Settings.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> gradeCapacityRange = new Range<Integer>();
	public Range<Integer> getGradeCapacityRange() {
		return gradeCapacityRange;
	}
	public void setGradeCapacity(Range<Integer> gradeCapacityRange) {
		this.gradeCapacityRange = gradeCapacityRange;
	}

	private static final String[] RESTRICTIONS = {
			"settings.id = #{settingsList.settings.id}",

			"lower(settings.name) like concat(lower(#{settingsList.settings.name}),'%')",

			"settings.gradeCapacity >= #{settingsList.gradeCapacityRange.begin}",
			"settings.gradeCapacity <= #{settingsList.gradeCapacityRange.end}",

			"settings.defaultGender = #{settingsList.settings.defaultGender}",

			"settings.dateCreated <= #{settingsList.dateCreatedRange.end}",
			"settings.dateCreated >= #{settingsList.dateCreatedRange.begin}",};

	@Observer("archivedSettings")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Settings e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getGradeCapacity() != null ? e.getGradeCapacity() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDefaultGender() != null ? e.getDefaultGender() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("GradeCapacity" + ",");

		builder.append("DefaultGender" + ",");

		builder.append("\r\n");
	}
}
