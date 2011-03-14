package com.oreon.smartsis.web.action.hostel;

import com.oreon.smartsis.hostel.BedAllocation;

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

public abstract class BedAllocationActionBase extends BaseAction<BedAllocation>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private BedAllocation bedAllocation;

	@In(create = true, value = "bedAction")
	com.oreon.smartsis.web.action.hostel.BedAction bedAction;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@DataModel
	private List<BedAllocation> bedAllocationRecordList;

	public void setBedAllocationId(Long id) {
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
	public void setBedAllocationIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setBedId(Long id) {

		if (id != null && id > 0)
			getInstance().setBed(bedAction.loadFromId(id));

	}

	public Long getBedId() {
		if (getInstance().getBed() != null)
			return getInstance().getBed().getId();
		return 0L;
	}

	public void setStudentId(Long id) {

		if (id != null && id > 0)
			getInstance().setStudent(studentAction.loadFromId(id));

	}

	public Long getStudentId() {
		if (getInstance().getStudent() != null)
			return getInstance().getStudent().getId();
		return 0L;
	}

	public Long getBedAllocationId() {
		return (Long) getId();
	}

	public BedAllocation getEntity() {
		return bedAllocation;
	}

	//@Override
	public void setEntity(BedAllocation t) {
		this.bedAllocation = t;
		loadAssociations();
	}

	public BedAllocation getBedAllocation() {
		return (BedAllocation) getInstance();
	}

	@Override
	protected BedAllocation createInstance() {
		return new BedAllocation();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.hostel.Bed bed = bedAction.getDefinedInstance();
		if (bed != null && isNew()) {
			getInstance().setBed(bed);
		}

		com.oreon.smartsis.domain.Student student = studentAction
				.getDefinedInstance();
		if (student != null && isNew()) {
			getInstance().setStudent(student);
		}

	}

	public boolean isWired() {
		return true;
	}

	public BedAllocation getDefinedInstance() {
		return (BedAllocation) (isIdDefined() ? getInstance() : null);
	}

	public void setBedAllocation(BedAllocation t) {
		this.bedAllocation = t;
		loadAssociations();
	}

	@Override
	public Class<BedAllocation> getEntityClass() {
		return BedAllocation.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (bedAllocation.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", bedAllocation
					.getBed().getId()));
		}

		if (bedAllocation.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id", bedAllocation
					.getStudent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (bedAllocation.getBed() != null) {
			bedAction.setInstance(getInstance().getBed());
		}

		if (bedAllocation.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
