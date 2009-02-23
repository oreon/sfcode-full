package com.wcmda.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.cerebrum.seam.action.base.BaseAction;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

@Scope(ScopeType.CONVERSATION)
@Name("studentAction")
public class StudentAction extends BaseAction{

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Student student;

	@DataModel
	private List<Student> studentList;

	private String typedUserName;
	

	@Factory("studentList")
	public void findStduents() {
		studentList = entityManager
				.createQuery(
						"select student from Student student order by student.lastName")
				.getResultList();
	}

	public void invalid() {
		facesMessages.add("Please try again");
	}

	@Begin
	public String select(Student student) {
		this.student = entityManager.merge(student);
	    log.info("User selected project: #{student.lastName} #{student.id} #{student.firstName}");
		return "/editStudent.jspx";
	}
	
	@End
	public String save() {
		facesMessages.add("Successfully saved  #{student.lastName}");
		entityManager.persist(student);
		return "/studentList.jspx";
	}

	public List<SelectItem> getUserNames() {
		List<SelectItem> users = new ArrayList<SelectItem>();
		if (StringUtils.length(typedUserName) == 0)
			return users;

		//String[] arr = { "mike", "michelle", "austin", "victor", "vincent",
		//		"april" };

		for (Student student : studentList) {

			if (student.getLastName().startsWith(typedUserName)) {
				SelectItem item = new SelectItem();
				item.setLabel(student.getLastName());
				users.add(item);
			}
		}
		return users;
	}

	public void selectInputValueChanged(ValueChangeEvent event) {

		if (event.getComponent() instanceof SelectInputText) {

			// get the number of displayable records from the component
			SelectInputText autoComplete = (SelectInputText) event
					.getComponent();
			// get the new value typed by component user.
			typedUserName = (String) event.getNewValue();
		}
	}

}
