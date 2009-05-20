package org.cerebrum.domain.patient.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.cerebrum.domain.patient.Document;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;

@Scope(ScopeType.SESSION)
@Name("documentAction")
public class DocumentAction extends BaseAction<Document> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Document document;
	
	 private FileInfo currentFile;

	@DataModel
	private List<Document> documentList;

	@Factory("documentList")
	@Observer("archivedDocument")
	public void findRecords() {
		search();
	}

	public Document getEntity() {
		return document;
	}

	@Override
	public void setEntity(Document t) {
		this.document = t;
	}

	@Override
	public void setEntityList(List<Document> list) {
		this.documentList = list;
	}

	/**
	 * This function adds associated entities to an example criterion
	 * 
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (document.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", document
					.getPatient().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Document> getEntityList() {
		if (documentList == null) {
			findRecords();
		}
		return documentList;
	}

	public void uploadFile(ActionEvent event) {
		  InputFile inputFile = (InputFile) event.getSource();
	        FileInfo fileInfo = inputFile.getFileInfo();
	        if (fileInfo.getStatus() == FileInfo.SAVED) {
	            // reference our newly updated file for display purposes and
	            // added it to our history file list.
	            currentFile = fileInfo;
	        }

	}

	public String save() {
		try {
			document.setFile(getBytesFromFile(currentFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.save();
		
	}
	
	// Returns the contents of the file in a byte array.
	private byte[] getBytesFromFile(FileInfo inputFile) throws IOException {

		File file = inputFile.getFile();
		InputStream inputStream = new FileInputStream(file);
		// Get the size of the file
		long length = Long.valueOf(file.length());

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = inputStream.read(bytes, offset, bytes.length
						- offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		inputStream.close();
		return bytes;
	}
}
