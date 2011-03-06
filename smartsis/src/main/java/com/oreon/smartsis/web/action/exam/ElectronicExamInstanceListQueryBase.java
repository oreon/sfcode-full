package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExamInstance;

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

import com.oreon.smartsis.exam.ElectronicExamInstance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ElectronicExamInstanceListQueryBase
		extends
			BaseQuery<ElectronicExamInstance, Long> {

	private static final String EJBQL = "select electronicExamInstance from ElectronicExamInstance electronicExamInstance";

	protected ElectronicExamInstance electronicExamInstance = new ElectronicExamInstance();

	public ElectronicExamInstance getElectronicExamInstance() {
		return electronicExamInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ElectronicExamInstance> getEntityClass() {
		return ElectronicExamInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> scoreRange = new Range<Integer>();
	public Range<Integer> getScoreRange() {
		return scoreRange;
	}
	public void setScore(Range<Integer> scoreRange) {
		this.scoreRange = scoreRange;
	}

	private Range<Integer> timeTakenRange = new Range<Integer>();
	public Range<Integer> getTimeTakenRange() {
		return timeTakenRange;
	}
	public void setTimeTaken(Range<Integer> timeTakenRange) {
		this.timeTakenRange = timeTakenRange;
	}

	private static final String[] RESTRICTIONS = {
			"electronicExamInstance.id = #{electronicExamInstanceList.electronicExamInstance.id}",

			"electronicExamInstance.student.id = #{electronicExamInstanceList.electronicExamInstance.student.id}",

			"electronicExamInstance.score >= #{electronicExamInstanceList.scoreRange.begin}",
			"electronicExamInstance.score <= #{electronicExamInstanceList.scoreRange.end}",

			"electronicExamInstance.electronicExamEvent.id = #{electronicExamInstanceList.electronicExamInstance.electronicExamEvent.id}",

			"electronicExamInstance.timeTaken >= #{electronicExamInstanceList.timeTakenRange.begin}",
			"electronicExamInstance.timeTaken <= #{electronicExamInstanceList.timeTakenRange.end}",

			"electronicExamInstance.dateCreated <= #{electronicExamInstanceList.dateCreatedRange.end}",
			"electronicExamInstance.dateCreated >= #{electronicExamInstanceList.dateCreatedRange.begin}",};

	public List<ElectronicExamInstance> getElectronicExamInstancesByElectronicExamEvent(
			com.oreon.smartsis.exam.ElectronicExamEvent electronicExamEvent) {
		//setMaxResults(10000);
		electronicExamInstance.setElectronicExamEvent(electronicExamEvent);
		return getResultList();
	}

	@Observer("archivedElectronicExamInstance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ElectronicExamInstance e) {

		builder.append("\""
				+ (e.getStudent() != null ? e.getStudent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getScore() != null ? e.getScore() : "")
				+ "\",");

		builder.append("\""
				+ (e.getElectronicExamEvent() != null ? e
						.getElectronicExamEvent().getDisplayName().replace(",",
								"") : "") + "\",");

		builder.append("\""
				+ (e.getTimeTaken() != null ? e.getTimeTaken() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Student" + ",");

		builder.append("Score" + ",");

		builder.append("ElectronicExamEvent" + ",");

		builder.append("TimeTaken" + ",");

		builder.append("\r\n");
	}
}
