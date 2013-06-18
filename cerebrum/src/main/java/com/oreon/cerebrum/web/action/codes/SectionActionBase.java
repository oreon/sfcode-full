package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.Section;

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
import org.jboss.seam.annotations.security.Restrict;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.codes.Code;

public abstract class SectionActionBase
		extends
			com.oreon.cerebrum.web.action.codes.AbstractAbstractCodeAction<Section>
		implements
			java.io.Serializable {

	@In(create = true, value = "chapterAction")
	com.oreon.cerebrum.web.action.codes.ChapterAction chapterAction;

	public void setSectionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		instance = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSectionIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setChapterId(Long id) {

		if (id != null && id > 0)
			getInstance().setChapter(chapterAction.loadFromId(id));

	}

	public Long getChapterId() {
		if (getInstance().getChapter() != null)
			return getInstance().getChapter().getId();
		return 0L;
	}

	public Long getSectionId() {
		return (Long) getId();
	}

	public Section getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Section t) {
		this.instance = t;
		loadAssociations();
	}

	public Section getSection() {
		return (Section) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('section', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('section', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Section createInstance() {
		Section instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.codes.Chapter chapter = chapterAction
				.getDefinedInstance();
		if (chapter != null && isNew()) {
			getInstance().setChapter(chapter);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Section getDefinedInstance() {
		return (Section) (isIdDefined() ? getInstance() : null);
	}

	public void setSection(Section t) {
		this.instance = t;
		if (getInstance() != null)
			setSectionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Section> getEntityClass() {
		return Section.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getChapter() != null) {
			criteria = criteria.add(Restrictions.eq("chapter.id", instance
					.getChapter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getChapter() != null) {
			chapterAction.setInstance(getInstance().getChapter());
			chapterAction.loadAssociations();
		}

		initListCodes();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.codes.Code> listCodes = new ArrayList<com.oreon.cerebrum.codes.Code>();

	void initListCodes() {

		if (listCodes.isEmpty())
			listCodes.addAll(getInstance().getCodes());

	}

	public List<com.oreon.cerebrum.codes.Code> getListCodes() {

		prePopulateListCodes();
		return listCodes;
	}

	public void prePopulateListCodes() {
	}

	public void setListCodes(List<com.oreon.cerebrum.codes.Code> listCodes) {
		this.listCodes = listCodes;
	}

	public void deleteCodes(int index) {
		listCodes.remove(index);
	}

	@Begin(join = true)
	public void addCodes() {

		initListCodes();
		Code codes = new Code();

		codes.setSection(getInstance());

		getListCodes().add(codes);

	}

	public void updateComposedAssociations() {

		if (listCodes != null) {
			getInstance().getCodes().clear();
			getInstance().getCodes().addAll(listCodes);
		}
	}

	public void clearLists() {
		listCodes.clear();

	}

	public String viewSection() {
		load(currentEntityId);
		return "viewSection";
	}

}
