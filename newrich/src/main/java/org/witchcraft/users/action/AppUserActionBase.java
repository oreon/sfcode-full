package org.witchcraft.users.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.users.AppRole;
import org.witchcraft.users.AppUser;

public abstract class AppUserActionBase extends BaseAction<AppUser>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private AppUser appUser;

	@DataModel
	private List<AppUser> appUserRecordList;

	public void setAppUserId(Long id) {
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
	public void setAppUserIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getAppUserId() {
		return (Long) getId();
	}

	public AppUser getEntity() {
		return appUser;
	}

	//@Override
	public void setEntity(AppUser t) {
		this.appUser = t;
		loadAssociations();
	}

	public AppUser getAppUser() {
		return (AppUser) getInstance();
	}

	@Override
	protected AppUser createInstance() {
		AppUser instance = super.createInstance();

		return instance;
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

	public AppUser getDefinedInstance() {
		return (AppUser) (isIdDefined() ? getInstance() : null);
	}

	public void setAppUser(AppUser t) {
		this.appUser = t;
		if (appUser != null)
			setAppUserId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	public AppUser findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("appUser.findByUnqUserName",
				userName);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListAppRoles();
		initListAvailableAppRoles();

	}

	public void updateAssociations() {

	}

	protected List<AppRole> listAppRoles = new ArrayList<AppRole>();

	void initListAppRoles() {

		if (listAppRoles.isEmpty())
			listAppRoles.addAll(getInstance().getAppRoles());

	}

	public List<AppRole> getListAppRoles() {

		prePopulateListAppRoles();
		return listAppRoles;
	}

	public void prePopulateListAppRoles() {
	}

	public void setListAppRoles(
			List<AppRole> listAppRoles) {
		this.listAppRoles = listAppRoles;
	}

	protected List<AppRole> listAvailableAppRoles = new ArrayList<AppRole>();

	void initListAvailableAppRoles() {

		listAvailableAppRoles = getEntityManager().createQuery(
				"select r from AppRole r").getResultList();
		listAvailableAppRoles.removeAll(getInstance().getAppRoles());

	}

	@Begin(join = true)
	public List<AppRole> getListAvailableAppRoles() {

		prePopulateListAvailableAppRoles();
		return listAvailableAppRoles;
	}

	public void prePopulateListAvailableAppRoles() {
	}

	public void setListAvailableAppRoles(
			List<AppRole> listAvailableAppRoles) {
		this.listAvailableAppRoles = listAvailableAppRoles;
	}

	public void updateComposedAssociations() {

		if (listAppRoles != null) {
			getInstance().getAppRoles().clear();
			getInstance().getAppRoles().addAll(listAppRoles);
		}
	}

	public void clearLists() {

		listAppRoles.clear();

	}

	public String viewAppUser() {
		load(currentEntityId);
		return "viewAppUser";
	}

}
