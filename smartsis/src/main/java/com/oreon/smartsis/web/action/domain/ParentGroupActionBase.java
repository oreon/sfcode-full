package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.ParentGroup;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.domain.Parent;

public abstract class ParentGroupActionBase extends BaseAction<ParentGroup>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ParentGroup parentGroup;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentsAction;

	@DataModel
	private List<ParentGroup> parentGroupRecordList;

	public void setParentGroupId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setParentGroupIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getParentGroupId() {
		return (Long) getId();
	}

	public ParentGroup getEntity() {
		return parentGroup;
	}

	//@Override
	public void setEntity(ParentGroup t) {
		this.parentGroup = t;
		loadAssociations();
	}

	public ParentGroup getParentGroup() {
		return (ParentGroup) getInstance();
	}

	@Override
	protected ParentGroup createInstance() {
		return new ParentGroup();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public ParentGroup getDefinedInstance() {
		return (ParentGroup) (isIdDefined() ? getInstance() : null);
	}

	public void setParentGroup(ParentGroup t) {
		this.parentGroup = t;
		if (parentGroup != null)
			setParentGroupId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ParentGroup> getEntityClass() {
		return ParentGroup.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListStudents();

		initListParents();

	}

	public void updateAssociations() {

		com.oreon.smartsis.domain.Student students = (com.oreon.smartsis.domain.Student) org.jboss.seam.Component
				.getInstance("student");
		students.setParentGroup(parentGroup);
		events.raiseTransactionSuccessEvent("archivedStudent");

	}

	protected List<com.oreon.smartsis.domain.Student> listStudents = new ArrayList<com.oreon.smartsis.domain.Student>();

	void initListStudents() {

		if (listStudents.isEmpty())
			listStudents.addAll(getInstance().getStudents());

	}

	public List<com.oreon.smartsis.domain.Student> getListStudents() {

		prePopulateListStudents();
		return listStudents;
	}

	public void prePopulateListStudents() {
	}

	public void setListStudents(
			List<com.oreon.smartsis.domain.Student> listStudents) {
		this.listStudents = listStudents;
	}

	public void deleteStudents(int index) {
		listStudents.remove(index);
	}

	@Begin(join = true)
	public void addStudents() {
		initListStudents();
		Student students = new Student();

		students.setParentGroup(getInstance());

		getListStudents().add(students);
	}

	protected List<com.oreon.smartsis.domain.Parent> listParents = new ArrayList<com.oreon.smartsis.domain.Parent>();

	void initListParents() {

		if (listParents.isEmpty())
			listParents.addAll(getInstance().getParents());

	}

	public List<com.oreon.smartsis.domain.Parent> getListParents() {

		prePopulateListParents();
		return listParents;
	}

	public void prePopulateListParents() {
	}

	public void setListParents(
			List<com.oreon.smartsis.domain.Parent> listParents) {
		this.listParents = listParents;
	}

	public void deleteParents(int index) {
		listParents.remove(index);
	}

	@Begin(join = true)
	public void addParents() {
		initListParents();
		Parent parents = new Parent();

		parents.setParentGroup(getInstance());

		getListParents().add(parents);
	}

	public void updateComposedAssociations() {

		if (listStudents != null) {
			getInstance().getStudents().clear();
			getInstance().getStudents().addAll(listStudents);
		}

		if (listParents != null) {
			getInstance().getParents().clear();
			getInstance().getParents().addAll(listParents);
		}
	}

	public void clearLists() {
		listStudents.clear();
		listParents.clear();

	}

}
