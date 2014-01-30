package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Exam;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.domain.Exam;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ExamListQueryBase extends BaseQuery<Exam, Long> {

	private static final String EJBQL = "select exam from Exam exam";

	protected Exam exam = new Exam();

	@In(create = true)
	ExamAction examAction;

	public ExamListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Exam getExam() {
		return exam;
	}

	@Override
	public Exam getInstance() {
		return getExam();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('exam', 'view')}")
	public List<Exam> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"exam.id = #{examList.exam.id}",

			"exam.archived = #{examList.exam.archived}",

			"lower(exam.title) like concat(lower(#{examList.exam.title}),'%')",

			"lower(exam.description) like concat(lower(#{examList.exam.description}),'%')",

			"exam.dateCreated <= #{examList.dateCreatedRange.end}",
			"exam.dateCreated >= #{examList.dateCreatedRange.begin}",};

	@Observer("archivedExam")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('exam', 'delete')}")
	public void archiveById(Long id) {
		examAction.archiveById(id);
		refresh();
	}

}
