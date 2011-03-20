package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.Claim;

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

import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

public abstract class ClaimActionBase extends BaseAction<Claim>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Claim claim;

	@In(create = true, value = "policyAction")
	com.pwc.insuranceclaims.web.action.quickclaim.PolicyAction policyAction;

	@In(create = true, value = "claimDocumentAction")
	com.pwc.insuranceclaims.web.action.quickclaim.ClaimDocumentAction claimDocumentsAction;

	@DataModel
	private List<Claim> claimRecordList;

	public void setClaimId(Long id) {
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
	public void setClaimIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setPolicyId(Long id) {

		if (id != null && id > 0)
			getInstance().setPolicy(policyAction.loadFromId(id));

	}

	public Long getPolicyId() {
		if (getInstance().getPolicy() != null)
			return getInstance().getPolicy().getId();
		return 0L;
	}

	public Long getClaimId() {
		return (Long) getId();
	}

	public Claim getEntity() {
		return claim;
	}

	//@Override
	public void setEntity(Claim t) {
		this.claim = t;
		loadAssociations();
	}

	public Claim getClaim() {
		return (Claim) getInstance();
	}

	@Override
	protected Claim createInstance() {
		return new Claim();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pwc.insuranceclaims.quickclaim.Policy policy = policyAction
				.getDefinedInstance();
		if (policy != null && isNew()) {
			getInstance().setPolicy(policy);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Claim getDefinedInstance() {
		return (Claim) (isIdDefined() ? getInstance() : null);
	}

	public void setClaim(Claim t) {
		this.claim = t;
		loadAssociations();
	}

	@Override
	public Class<Claim> getEntityClass() {
		return Claim.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (claim.getPolicy() != null) {
			criteria = criteria.add(Restrictions.eq("policy.id", claim
					.getPolicy().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (claim.getPolicy() != null) {
			policyAction.setInstance(getInstance().getPolicy());
		}

		initListClaimDocuments();

	}

	public void updateAssociations() {

		com.pwc.insuranceclaims.quickclaim.ClaimDocument claimDocuments = (com.pwc.insuranceclaims.quickclaim.ClaimDocument) org.jboss.seam.Component
				.getInstance("claimDocument");
		claimDocuments.setClaim(claim);
		events.raiseTransactionSuccessEvent("archivedClaimDocument");

	}

	protected List<com.pwc.insuranceclaims.quickclaim.ClaimDocument> listClaimDocuments = new ArrayList<com.pwc.insuranceclaims.quickclaim.ClaimDocument>();

	void initListClaimDocuments() {

		if (listClaimDocuments.isEmpty())
			listClaimDocuments.addAll(getInstance().getClaimDocuments());

	}

	public List<com.pwc.insuranceclaims.quickclaim.ClaimDocument> getListClaimDocuments() {

		prePopulateListClaimDocuments();
		return listClaimDocuments;
	}

	public void prePopulateListClaimDocuments() {
	}

	public void setListClaimDocuments(
			List<com.pwc.insuranceclaims.quickclaim.ClaimDocument> listClaimDocuments) {
		this.listClaimDocuments = listClaimDocuments;
	}

	public void deleteClaimDocuments(int index) {
		listClaimDocuments.remove(index);
	}

	@Begin(join = true)
	public void addClaimDocuments() {
		initListClaimDocuments();
		ClaimDocument claimDocuments = new ClaimDocument();

		claimDocuments.setClaim(getInstance());

		getListClaimDocuments().add(claimDocuments);
	}

	public void updateComposedAssociations() {

		if (listClaimDocuments != null) {
			getInstance().getClaimDocuments().clear();
			getInstance().getClaimDocuments().addAll(listClaimDocuments);
		}
	}

	public void clearLists() {
		listClaimDocuments.clear();

	}

}
