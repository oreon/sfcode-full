package com.pcas.datapkg.web.action.customReports;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.pcas.datapkg.customReports.EntityPrevilige;
import com.pcas.datapkg.customReports.FieldPrevilige;

public abstract class EntityPreviligeActionBase
		extends
			BaseAction<EntityPrevilige> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private EntityPrevilige entityPrevilige;

	@In(create = true, value = "metaEntityAction")
	com.pcas.datapkg.web.action.customReports.MetaEntityAction metaEntityAction;

	@DataModel
	private List<EntityPrevilige> entityPreviligeRecordList;

	public void setEntityPreviligeId(Long id) {
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
	public void setEntityPreviligeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMetaEntityId(Long id) {

		if (id != null && id > 0)
			getInstance().setMetaEntity(metaEntityAction.loadFromId(id));

	}

	public Long getMetaEntityId() {
		if (getInstance().getMetaEntity() != null)
			return getInstance().getMetaEntity().getId();
		return 0L;
	}

	public Long getEntityPreviligeId() {
		return (Long) getId();
	}

	public EntityPrevilige getEntity() {
		return entityPrevilige;
	}

	//@Override
	public void setEntity(EntityPrevilige t) {
		this.entityPrevilige = t;
		loadAssociations();
	}

	public EntityPrevilige getEntityPrevilige() {
		return (EntityPrevilige) getInstance();
	}

	@Override
	protected EntityPrevilige createInstance() {
		EntityPrevilige instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.pcas.datapkg.customReports.MetaEntity metaEntity = metaEntityAction
				.getDefinedInstance();
		if (metaEntity != null && isNew()) {
			getInstance().setMetaEntity(metaEntity);
		}

	}

	public boolean isWired() {
		return true;
	}

	public EntityPrevilige getDefinedInstance() {
		return (EntityPrevilige) (isIdDefined() ? getInstance() : null);
	}

	public void setEntityPrevilige(EntityPrevilige t) {
		this.entityPrevilige = t;
		if (entityPrevilige != null)
			setEntityPreviligeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<EntityPrevilige> getEntityClass() {
		return EntityPrevilige.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (entityPrevilige.getMetaEntity() != null) {
			criteria = criteria.add(Restrictions.eq("metaEntity.id",
					entityPrevilige.getMetaEntity().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (entityPrevilige.getMetaEntity() != null) {
			metaEntityAction.setInstance(getInstance().getMetaEntity());
		}

		initListFieldPreviliges();

	}

	public void updateAssociations() {

	}

	protected List<com.pcas.datapkg.customReports.FieldPrevilige> listFieldPreviliges = new ArrayList<com.pcas.datapkg.customReports.FieldPrevilige>();

	void initListFieldPreviliges() {

		if (listFieldPreviliges.isEmpty())
			listFieldPreviliges.addAll(getInstance().getFieldPreviliges());

	}

	public List<com.pcas.datapkg.customReports.FieldPrevilige> getListFieldPreviliges() {

		prePopulateListFieldPreviliges();
		return listFieldPreviliges;
	}

	public void prePopulateListFieldPreviliges() {
	}

	public void setListFieldPreviliges(
			List<com.pcas.datapkg.customReports.FieldPrevilige> listFieldPreviliges) {
		this.listFieldPreviliges = listFieldPreviliges;
	}

	public void deleteFieldPreviliges(int index) {
		listFieldPreviliges.remove(index);
	}

	@Begin(join = true)
	public void addFieldPreviliges() {
		initListFieldPreviliges();
		FieldPrevilige fieldPreviliges = new FieldPrevilige();

		fieldPreviliges.setEntityPrevilige(getInstance());

		getListFieldPreviliges().add(fieldPreviliges);
	}

	public void updateComposedAssociations() {

		if (listFieldPreviliges != null) {
			getInstance().getFieldPreviliges().clear();
			getInstance().getFieldPreviliges().addAll(listFieldPreviliges);
		}
	}

	public void clearLists() {
		listFieldPreviliges.clear();

	}

	public String viewEntityPrevilige() {
		load(currentEntityId);
		return "viewEntityPrevilige";
	}

}
