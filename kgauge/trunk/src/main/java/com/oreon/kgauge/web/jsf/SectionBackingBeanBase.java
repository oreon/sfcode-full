package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Section;
import com.oreon.kgauge.service.SectionService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class SectionBackingBeanBase extends BaseBackingBean<Section> {

	protected Section section = new Section();

	protected SectionService sectionService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public Section getSection() {
		return section;
	}

	public void set(Section section) {
		this.section = section;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Section> getBaseService() {
		return sectionService;
	}

	public Section getEntity() {
		return getSection();
	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent actionEvent) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		section = sectionService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(section);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
