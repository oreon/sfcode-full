package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.DifficultyLevel;
import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.service.QuestionService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.witchcraft.model.support.Range;

public class QuestionBackingBean extends QuestionBackingBeanBase {

	

	/**
	 * This action Listener Method is called when a row is clicked in the
	 * dataTable
	 * 
	 * @param event
	 *            contains the database id of the row being selected
	 */
	public void selectEntity(ActionEvent actionEvent) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		question = questionService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(question);
		}
	}

	// ****************Testing***********************************/
	/** A method to fetch any attributes value from the page */
	private String getValue(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> a = ctx.getViewRoot().getAttributes();
		String p = (String) a.get(s);
		return p;
	}

	public void setDifficultyLevel() {
		int dl = Integer.parseInt(getValue("question_difficultyLevel"));
		switch (dl) {
		case 1:
			this.question.setDifficultyLevel(DifficultyLevel.L1);
			break;
		case 2:
			this.question.setDifficultyLevel(DifficultyLevel.L2);
			break;
		case 3:
			this.question.setDifficultyLevel(DifficultyLevel.L3);
			break;
		case 4:
			this.question.setDifficultyLevel(DifficultyLevel.L4);
			break;
		}
	}

	// ****************Testing***********************************/

}
