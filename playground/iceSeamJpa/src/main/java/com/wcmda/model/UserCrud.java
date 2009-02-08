package com.wcmda.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

@Scope(ScopeType.EVENT)
@Name("register")
public class UserCrud {

	@In
	@DataModelSelection
	@Out(required = false)
	private User user;

	@DataModel
	private List<User> userList;

	/*
	@DataModelSelection
	@Out(required = false)
	private User selectedUser;*/

	@In
	// @PersistenceContext(type=EXTENDED)
	EntityManager entityManager;

	@In
	private FacesMessages facesMessages;

	private boolean registered;

	private String typedUserName;

	public void register() {
		List existing = entityManager
				.createQuery(
						"select u.username from User u where u.username=#{user.username}")
				.getResultList();
		if (existing.size() == 0) {
			entityManager.persist(user);
			facesMessages.add("Successfully registered as #{user.username}");
			registered = true;
		} else {
			facesMessages.addToControl("username",
					"Username #{user.username} already exists");
		}
	}

	@Factory("userList")
	public void findMessages() {
		userList = entityManager.createQuery(
				"select user from User user order by user.name")
				.getResultList();
	}

	public void invalid() {
		facesMessages.add("Please try again");
	}

	public boolean isRegistered() {
		return registered;
	}
	
	public String select(){
		return "/one.jspx";
	}

	public List<SelectItem> getUserNames() {
		List<SelectItem> users = new ArrayList<SelectItem>();
		if (StringUtils.length(typedUserName) == 0)
			return users;

		String[] arr = { "mike", "michelle", "austin", "victor", "vincent",
				"april" };

		for (String userName : arr) {

			if (userName.startsWith(typedUserName)) {
				SelectItem item = new SelectItem();
				item.setLabel(userName);
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
