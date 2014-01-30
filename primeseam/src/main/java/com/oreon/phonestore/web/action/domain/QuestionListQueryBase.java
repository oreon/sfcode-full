package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Question;

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

import com.oreon.phonestore.domain.Question;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionListQueryBase extends BaseQuery<Question, Long> {

	private static final String EJBQL = "select question from Question question";

	protected Question question = new Question();

	@In(create = true)
	QuestionAction questionAction;

	public QuestionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Question getQuestion() {
		return question;
	}

	@Override
	public Question getInstance() {
		return getQuestion();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('question', 'view')}")
	public List<Question> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"question.id = #{questionList.question.id}",

			"question.archived = #{questionList.question.archived}",

			"lower(question.text) like concat(lower(#{questionList.question.text}),'%')",

			"question.exam.id = #{questionList.question.exam.id}",

			"question.dateCreated <= #{questionList.dateCreatedRange.end}",
			"question.dateCreated >= #{questionList.dateCreatedRange.begin}",};

	public LazyDataModel<Question> getQuestionsByExam(
			final com.oreon.phonestore.domain.Exam exam) {

		EntityLazyDataModel<Question, Long> questionLazyDataModel = new EntityLazyDataModel<Question, Long>(
				this) {

			@Override
			public List<Question> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				question.setExam(exam);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return questionLazyDataModel;

	}

	@Observer("archivedQuestion")
	public void onArchive() {
		refresh();
	}

	public void setExamId(Long id) {
		if (question.getExam() == null) {
			question.setExam(new com.oreon.phonestore.domain.Exam());
		}
		question.getExam().setId(id);
	}

	public Long getExamId() {
		return question.getExam() == null ? null : question.getExam().getId();
	}

	//@Restrict("#{s:hasPermission('question', 'delete')}")
	public void archiveById(Long id) {
		questionAction.archiveById(id);
		refresh();
	}

}
