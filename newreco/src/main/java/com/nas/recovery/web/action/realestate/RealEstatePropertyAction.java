package com.nas.recovery.web.action.realestate;

import org.jboss.seam.annotations.Name;

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
