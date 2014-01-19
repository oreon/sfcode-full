package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class ExamDto extends BaseEntity {

	protected String title;

	protected String description;

	private Set<QuestionDto> questionsDto = new HashSet<QuestionDto>();

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setQuestions(Set<QuestionDto> questionsDto) {
		this.questionsDto = questionsDto;
	}
	public Set<QuestionDto> getQuestions() {
		return questionsDto;
	}

}
