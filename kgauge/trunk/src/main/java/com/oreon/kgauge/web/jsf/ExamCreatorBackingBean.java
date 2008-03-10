package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.ExamCreator;
import com.oreon.kgauge.service.ExamCreatorService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

public class ExamCreatorBackingBean extends BaseBackingBean<ExamCreator> {

	private ExamCreator examCreator = new ExamCreator();

	private ExamCreatorService examCreatorService;

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Date> rangeDateOfBirth = new Range<Date>("dateOfBirth");

	public Range<Date> getRangeDateOfBirth() {
		return rangeDateOfBirth;
	}

	public void setRangeDateOfBirth(Range<Date> rangeDateOfBirth) {
		this.rangeDateOfBirth = rangeDateOfBirth;
	}

	public void setExamCreatorService(ExamCreatorService examCreatorService) {
		this.examCreatorService = examCreatorService;
	}

	public ExamCreator getExamCreator() {
		return examCreator;
	}

	public void set(ExamCreator examCreator) {
		this.examCreator = examCreator;
	}

	@SuppressWarnings("unchecked")
	public BaseService<ExamCreator> getBaseService() {
		return examCreatorService;
	}

	public ExamCreator getEntity() {
		return getExamCreator();
	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeDateOfBirth);

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
		examCreator = examCreatorService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(examCreator);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
