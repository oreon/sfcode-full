package com.oreon.phonestore.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class QuestionDto extends BaseEntity {

	protected String text;

	protected ExamDto examDto;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setExam(ExamDto examDto) {
		this.examDto = examDto;
	}

	public ExamDto getExam() {
		return examDto;
	}

}
