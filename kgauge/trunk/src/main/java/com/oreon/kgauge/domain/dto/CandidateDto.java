
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class CandidateDto {

	private String description;

	private java.util.Set<ExamInstanceDto> examInstance = new java.util.HashSet<ExamInstanceDto>();

	private UserDto user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDto getUser() {
		return this.user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public void addExamInstance(ExamInstanceDto examInstance) {
		//checkMaximumExamInstance();
		examInstance.setCandidate(this);
		this.examInstance.add(examInstance);
	}

	public void remove(ExamInstanceDto examInstance) {
		this.examInstance.remove(examInstance);
	}

	public java.util.Set<ExamInstanceDto> getExamInstance() {
		return this.examInstance;
	}

	public void setExamInstance(java.util.Set<ExamInstanceDto> examInstance) {
		this.examInstance = examInstance;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getExamInstanceCount() {
		return this.examInstance.size();
	}

	/*
	public void checkMaximumExamInstance(){
		// if(examInstance.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + examInstance );
	}*/

}
