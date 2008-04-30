package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.service.ExamInstanceService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class ExamInstanceBackingBeanBase extends BaseBackingBean<ExamInstance> {

	protected ExamInstance examInstance = new ExamInstance();

	protected ExamInstanceService examInstanceService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	public ExamInstance getExamInstance() {
		return examInstance;
	}

	public void set(ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	@SuppressWarnings("unchecked")
	public BaseService<ExamInstance> getBaseService() {
		return examInstanceService;
	}

	public ExamInstance getEntity() {
		return getExamInstance();
	}

	public void reset() {
		examInstance = new ExamInstance();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		examInstance = examInstanceService.load(id);
	}

}
