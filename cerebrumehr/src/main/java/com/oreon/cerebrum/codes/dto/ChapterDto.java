package com.oreon.cerebrum.codes.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class ChapterDto extends com.oreon.cerebrum.codes.AbstractCode {

	private Set<SectionDto> sectionsDto = new HashSet<SectionDto>();

	public void setSections(Set<SectionDto> sectionsDto) {
		this.sectionsDto = sectionsDto;
	}
	public Set<SectionDto> getSections() {
		return sectionsDto;
	}

}
