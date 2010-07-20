package com.nas.recovery.web.action.realestate;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.FileAttachment;

import com.nas.recovery.domain.realestate.FilesUploaded;

@Name("realEstatePropertyAction")
public class RealEstatePropertyAction extends RealEstatePropertyActionBase
		implements java.io.Serializable {

	private FilesUploaded filesUploaded = new FilesUploaded();

	public void uploadFile() {
		try {
			filesUploaded.setRealEstateProperty(getInstance());
			listFilesUploadeds.add(filesUploaded);
			super.save();
			filesUploaded = new FilesUploaded();
		} catch (Exception e) {
			statusMessages.add(e.getMessage());
		}

	}

	public void setFilesUploaded(FilesUploaded filesUploaded) {
		this.filesUploaded = filesUploaded;
	}

	public FilesUploaded getFilesUploaded() {
		return filesUploaded;
	}

}
