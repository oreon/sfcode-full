package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.Chapter;

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

import com.oreon.cerebrum.codes.Section;

public abstract class ChapterActionBase
		extends
			com.oreon.cerebrum.web.action.codes.AbstractAbstractCodeAction<Chapter>
		implements
			java.io.Serializable {

	public void setChapterId(Long id) {
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
	public void setChapterIdForModalDlg(Long id) {
		setId(id);
		instance = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getChapterId() {
		return (Long) getId();
	}

	public Chapter getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Chapter t) {
		this.instance = t;
		loadAssociations();
	}

	public Chapter getChapter() {
		return (Chapter) getInstance();
	}

	@Override
	//@Restrict("#{s:hasPermission('chapter', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	//@Restrict("#{s:hasPermission('chapter', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Chapter createInstance() {
		Chapter instance = super.createInstance();

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

	public Chapter getDefinedInstance() {
		return (Chapter) (isIdDefined() ? getInstance() : null);
	}

	public void setChapter(Chapter t) {
		this.instance = t;
		if (getInstance() != null)
			setChapterId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Chapter> getEntityClass() {
		return Chapter.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListSections();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.codes.Section> listSections = new ArrayList<com.oreon.cerebrum.codes.Section>();

	void initListSections() {

		if (listSections.isEmpty())
			listSections.addAll(getInstance().getSections());

	}

	public List<com.oreon.cerebrum.codes.Section> getListSections() {

		prePopulateListSections();
		return listSections;
	}

	public void prePopulateListSections() {
	}

	public void setListSections(
			List<com.oreon.cerebrum.codes.Section> listSections) {
		this.listSections = listSections;
	}

	public void deleteSections(int index) {
		listSections.remove(index);
	}

	@Begin(join = true)
	public void addSections() {

		initListSections();
		Section sections = new Section();

		sections.setChapter(getInstance());

		getListSections().add(sections);

	}

	public void updateComposedAssociations() {

		if (listSections != null) {
			getInstance().getSections().clear();
			getInstance().getSections().addAll(listSections);
		}
	}

	public void clearLists() {
		listSections.clear();

	}

	public String viewChapter() {
		load(currentEntityId);
		return "viewChapter";
	}

}