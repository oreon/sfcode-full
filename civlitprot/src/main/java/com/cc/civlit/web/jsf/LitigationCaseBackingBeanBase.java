package com.cc.civlit.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.LitigationCase;
import com.cc.civlit.service.LitigationCaseService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - LitigationCase class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public abstract class LitigationCaseBackingBeanBase
		extends
			BaseBackingBean<LitigationCase> {

	protected LitigationCase litigationCase = new LitigationCase();

	protected LitigationCaseService litigationCaseService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public LitigationCase getLitigationCase() {
		return litigationCase;
	}

	public void setLitigationCase(LitigationCase litigationCase) {
		this.litigationCase = litigationCase;
	}

	public void setLitigationCaseService(
			LitigationCaseService litigationCaseService) {
		this.litigationCaseService = litigationCaseService;
	}

	public LitigationCaseService getLitigationCaseService() {
		return this.litigationCaseService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<LitigationCase> getBaseService() {
		return litigationCaseService;
	}

	public LitigationCase getEntity() {
		return getLitigationCase();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {
		
	}

	public void reset() {
		litigationCase = new LitigationCase();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	

}
