package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

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

public abstract class ClaimDocumentActionBase extends BaseAction<ClaimDocument>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ClaimDocument claimDocument;

	@In(create = true, value = "claimAction")
	com.pwc.insuranceclaims.web.action.quickclaim.ClaimAction claimAction;

	@DataModel
	private List<ClaimDocument> claimDocumentRecordList;

	public void setClaimDocumentId(Long id) {
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
	public void setClaimDocumentIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setClaimId(Long id) {

		if (id != null && id > 0)
			getInstance().setClaim(claimAction.loadFromId(id));

	}

	public Long getClaimId() {
		if (getInstance().getClaim() != null)
			return getInstance().getClaim().getId();
		return 0L;
	}

	public Long getClaimDocumentId() {
		return (Long) getId();
	}

	public ClaimDocument getEntity() {
		return claimDocument;
	}

	//@Override
	public void setEntity(ClaimDocument t) {
		this.claimDocument = t;
		loadAssociations();
	}

	public ClaimDocument getClaimDocument() {
		return (ClaimDocument) getInstance();
	}

	@Override
	protected ClaimDocument createInstance() {
		return new ClaimDocument();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pwc.insuranceclaims.quickclaim.Claim claim = claimAction
				.getDefinedInstance();
		if (claim != null && isNew()) {
			getInstance().setClaim(claim);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ClaimDocument getDefinedInstance() {
		return (ClaimDocument) (isIdDefined() ? getInstance() : null);
	}

	public void setClaimDocument(ClaimDocument t) {
		this.claimDocument = t;
		loadAssociations();
	}

	@Override
	public Class<ClaimDocument> getEntityClass() {
		return ClaimDocument.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (claimDocument.getClaim() != null) {
			criteria = criteria.add(Restrictions.eq("claim.id", claimDocument
					.getClaim().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (claimDocument.getClaim() != null) {
			claimAction.setInstance(getInstance().getClaim());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
