package com.cc.civlit.domain.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.courtdivisions.Divsion;
import com.cc.civlit.domain.service.DivsionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Divsion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class DivsionBackingBeanBase extends BaseBackingBean<Divsion> {

	protected Divsion divsion = new Divsion();

	protected DivsionService divsionService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Divsion getDivsion() {
		return divsion;
	}

	public void setDivsion(Divsion divsion) {
		this.divsion = divsion;
	}

	public void setDivsionService(DivsionService divsionService) {
		this.divsionService = divsionService;
	}

	public DivsionService getDivsionService() {
		return this.divsionService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Divsion> getBaseService() {
		return divsionService;
	}

	public Divsion getEntity() {
		return getDivsion();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		divsion = new Divsion();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		divsion = divsionService.load(id);

	}

}
