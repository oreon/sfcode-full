package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.Chapter;

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

import com.oreon.cerebrum.codes.Chapter;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChapterListQueryBase extends BaseQuery<Chapter, Long> {

	private static final String EJBQL = "select chapter from Chapter chapter";

	protected Chapter chapter = new Chapter();

	public Chapter getChapter() {
		return chapter;
	}

	@Override
	public Chapter getInstance() {
		return getChapter();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('chapter', 'view')}")
	public List<Chapter> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Chapter> getEntityClass() {
		return Chapter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chapter.id = #{chapterList.chapter.id}",

			"chapter.archived = #{chapterList.chapter.archived}",

			"lower(chapter.name) like concat(lower(#{chapterList.chapter.name}),'%')",

			"lower(chapter.description) like concat(lower(#{chapterList.chapter.description}),'%')",

			"chapter.dateCreated <= #{chapterList.dateCreatedRange.end}",
			"chapter.dateCreated >= #{chapterList.dateCreatedRange.begin}",};

	@Observer("archivedChapter")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Chapter e) {

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
