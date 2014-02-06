package com.oreon.cerebrum.codes.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class SectionDto extends com.oreon.cerebrum.codes.AbstractCode {

	protected ChapterDto chapterDto;

	private Set<CodeDto> codesDto = new HashSet<CodeDto>();

	public void setChapter(ChapterDto chapterDto) {
		this.chapterDto = chapterDto;
	}

	public ChapterDto getChapter() {
		return chapterDto;
	}

	public void setCodes(Set<CodeDto> codesDto) {
		this.codesDto = codesDto;
	}
	public Set<CodeDto> getCodes() {
		return codesDto;
	}

}
