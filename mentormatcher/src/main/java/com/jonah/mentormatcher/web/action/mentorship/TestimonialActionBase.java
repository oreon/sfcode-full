package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.Testimonial;

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

public abstract class TestimonialActionBase extends BaseAction<Testimonial>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Testimonial testimonial;

	@In(create = true, value = "employeeAction")
	com.jonah.mentormatcher.web.action.domain.EmployeeAction employeeAction;

	@DataModel
	private List<Testimonial> testimonialRecordList;

	public void setTestimonialId(Long id) {
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
	public void setTestimonialIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setEmployeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setEmployee(employeeAction.loadFromId(id));

	}

	public Long getEmployeeId() {
		if (getInstance().getEmployee() != null)
			return getInstance().getEmployee().getId();
		return 0L;
	}

	public Long getTestimonialId() {
		return (Long) getId();
	}

	public Testimonial getEntity() {
		return testimonial;
	}

	//@Override
	public void setEntity(Testimonial t) {
		this.testimonial = t;
		loadAssociations();
	}

	public Testimonial getTestimonial() {
		return (Testimonial) getInstance();
	}

	@Override
	protected Testimonial createInstance() {
		return new Testimonial();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.jonah.mentormatcher.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null && isNew()) {
			getInstance().setEmployee(employee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Testimonial getDefinedInstance() {
		return (Testimonial) (isIdDefined() ? getInstance() : null);
	}

	public void setTestimonial(Testimonial t) {
		this.testimonial = t;
		if (testimonial != null)
			setTestimonialId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Testimonial> getEntityClass() {
		return Testimonial.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (testimonial.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id", testimonial
					.getEmployee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (testimonial.getEmployee() != null) {
			employeeAction.setInstance(getInstance().getEmployee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
