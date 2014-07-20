package com.oreon.cerebrum.web.action.users;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.users.AppRole;

//
public abstract class AppRoleActionBase extends BaseAction<AppRole>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long appRoleId;

	public void setAppRoleId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppRoleIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getAppRoleId() {
		return (Long) getId();
	}

	public AppRole getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(AppRole t) {
		this.instance = t;
		loadAssociations();
	}

	public AppRole getAppRole() {
		return (AppRole) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('appRole', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('appRole', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected AppRole createInstance() {
		AppRole instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

	}

	public AppRole getDefinedInstance() {
		return (AppRole) (isIdDefined() ? getInstance() : null);
	}

	public void setAppRole(AppRole t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAppRoleId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<AppRole> getEntityClass() {
		return AppRole.class;
	}

	public com.oreon.cerebrum.users.AppRole findByUnqName(String name) {
		return executeSingleResultNamedQuery("appRole.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewAppRole() {
		load(currentEntityId);
		return "viewAppRole";
	}

}
