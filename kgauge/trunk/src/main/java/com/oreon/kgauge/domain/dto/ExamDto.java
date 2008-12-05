
/**
 * This is generated code - to edit code or override methods use - Exam class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class ExamDto {

	private String examNumber;

	private String name;

	private Integer questions;

	private Integer duration;

	private Double price;

	private ScoringType scoringStrategy;

	private ExamStatus examStatus;

	private Integer passMarks;

	private Integer defaultMarksForCorrect;

	private CategoryDto category;

	private ExamCreatorDto examCreator;

	private java.util.Set<SectionDto> section = new java.util.HashSet<SectionDto>();

	public String getExamNumber() {
		return examNumber;
	}

	public void setExamNumber(String examNumber) {
		this.examNumber = examNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuestions() {
		return questions;
	}

	public void setQuestions(Integer questions) {
		this.questions = questions;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ScoringType getScoringStrategy() {
		return scoringStrategy;
	}

	public void setScoringStrategy(ScoringType scoringStrategy) {
		this.scoringStrategy = scoringStrategy;
	}

	public ExamStatus getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

	public Integer getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(Integer passMarks) {
		this.passMarks = passMarks;
	}

	public Integer getDefaultMarksForCorrect() {
		return defaultMarksForCorrect;
	}

	public void setDefaultMarksForCorrect(Integer defaultMarksForCorrect) {
		this.defaultMarksForCorrect = defaultMarksForCorrect;
	}

	public CategoryDto getCategory() {
		return this.category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public ExamCreatorDto getExamCreator() {
		return this.examCreator;
	}

	public void setExamCreator(ExamCreatorDto examCreator) {
		this.examCreator = examCreator;
	}

	public void addSection(SectionDto section) {
		//checkMaximumSection();
		section.setExam(this);
		this.section.add(section);
	}

	public void remove(SectionDto section) {
		this.section.remove(section);
	}

	public java.util.Set<SectionDto> getSection() {
		return this.section;
	}

	public void setSection(java.util.Set<SectionDto> section) {
		this.section = section;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getSectionCount() {
		return this.section.size();
	}

	/*
	public void checkMaximumSection(){
		// if(section.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + section );
	}*/

}
