package com.oreon.cerebrum.codes.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class CodeDto extends com.oreon.cerebrum.codes.AbstractCode {

	protected String includes;

	protected String notIncludedHere;

	protected String codeFirst;

	protected SectionDto sectionDto;

	protected String notCodedHere;

	protected String codeAlso;

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public String getIncludes() {
		return includes;
	}

	public void setNotIncludedHere(String notIncludedHere) {
		this.notIncludedHere = notIncludedHere;
	}

	public String getNotIncludedHere() {
		return notIncludedHere;
	}

	public void setCodeFirst(String codeFirst) {
		this.codeFirst = codeFirst;
	}

	public String getCodeFirst() {
		return codeFirst;
	}

	public void setSection(SectionDto sectionDto) {
		this.sectionDto = sectionDto;
	}

	public SectionDto getSection() {
		return sectionDto;
	}

	public void setNotCodedHere(String notCodedHere) {
		this.notCodedHere = notCodedHere;
	}

	public String getNotCodedHere() {
		return notCodedHere;
	}

	public void setCodeAlso(String codeAlso) {
		this.codeAlso = codeAlso;
	}

	public String getCodeAlso() {
		return codeAlso;
	}

}
