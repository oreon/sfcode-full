package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.Section;

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

import org.jboss.seam.annotations.security.Restrict;

import com.oreon.cerebrum.codes.Section;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SectionListQueryBase extends BaseQuery<Section, Long> {

	private static final String EJBQL = "select section from Section section";

	protected Section section = new Section();

	public Section getSection() {
		return section;
	}

	@Override
	public Section getInstance() {
		return getSection();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('section', 'view')}")
	public List<Section> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Section> getEntityClass() {
		return Section.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"section.id = #{sectionList.section.id}",

			"section.archived = #{sectionList.section.archived}",

			"lower(section.name) like concat(lower(#{sectionList.section.name}),'%')",

			"lower(section.description) like concat(lower(#{sectionList.section.description}),'%')",

			"section.chapter.id = #{sectionList.section.chapter.id}",

			"section.dateCreated <= #{sectionList.dateCreatedRange.end}",
			"section.dateCreated >= #{sectionList.dateCreatedRange.begin}",};

	public List<Section> getSectionsByChapter(
			com.oreon.cerebrum.codes.Chapter chapter) {
		section.setChapter(chapter);
		return getResultList();
	}

	@Observer("archivedSection")
	public void onArchive() {
		refresh();
	}

	public void setChapterId(Long id) {
		if (section.getChapter() == null) {
			section.setChapter(new com.oreon.cerebrum.codes.Chapter());
		}
		section.getChapter().setId(id);
	}

	public Long getChapterId() {
		return section.getChapter() == null ? null : section.getChapter()
				.getId();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Section e) {

		builder.append("\""
				+ (e.getChapter() != null ? e.getChapter().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Chapter" + ",");

		builder.append("\r\n");
	}
}
