package com.oreon.cerebrum.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.cerebrum.drugs.DrugCode;
import com.oreon.cerebrum.service.DrugCodeService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - DrugCode class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class DrugCodeBackingBeanBase extends BaseBackingBean<DrugCode> {

	protected DrugCode drugCode = new DrugCode();

	protected DrugCodeService drugCodeService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public DrugCode getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(DrugCode drugCode) {
		this.drugCode = drugCode;
	}

	public void setDrugCodeService(DrugCodeService drugCodeService) {
		this.drugCodeService = drugCodeService;
	}

	public DrugCodeService getDrugCodeService() {
		return this.drugCodeService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<DrugCode> getBaseService() {
		return drugCodeService;
	}

	public DrugCode getEntity() {
		return getDrugCode();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		drugCode = new DrugCode();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			drugCode = drugCodeService.load(id);

	}

	@Override
	public String update() {

		return super.update();
	}

}
