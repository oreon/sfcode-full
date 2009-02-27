package com.oreon.cerebrum.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.service.DrugService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Drug class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class DrugBackingBeanBase extends BaseBackingBean<Drug> {

	protected Drug drug = new Drug();

	protected DrugService drugService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Integer> rangeBioavalability = new Range<Integer>(
			"bioavalability");

	public Range<Integer> getRangeBioavalability() {
		return rangeBioavalability;
	}

	public void setRangeBioavalability(Range<Integer> rangeBioavalability) {
		this.rangeBioavalability = rangeBioavalability;
	}

	private Range<Integer> rangeHalfLife = new Range<Integer>("halfLife");

	public Range<Integer> getRangeHalfLife() {
		return rangeHalfLife;
	}

	public void setRangeHalfLife(Range<Integer> rangeHalfLife) {
		this.rangeHalfLife = rangeHalfLife;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public void setDrugService(DrugService drugService) {
		this.drugService = drugService;
	}

	public DrugService getDrugService() {
		return this.drugService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Drug> getBaseService() {
		return drugService;
	}

	public Drug getEntity() {
		return getDrug();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		drug = new Drug();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeBioavalability);

		listRanges.add(rangeHalfLife);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			drug = drugService.load(id);

	}

	@Override
	public String update() {

		return super.update();
	}

}
