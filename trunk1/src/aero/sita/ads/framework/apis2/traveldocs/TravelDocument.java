package aero.sita.ads.framework.apis2.traveldocs;
import java.util.Date;
import java.util.Map;

import aero.sita.ads.framework.apis2.AbstractAPISInfoComponent;

import sita.ads.apx.framework.businesslogic.BLConstants;



/**
 * This travel document can represent - visa, passport, alien reg card etc
 * The actual name should be abstract travel document 
 * @author jsingh
 *
 */
public abstract class TravelDocument extends AbstractAPISInfoComponent  {

	//public static final int DOC_TYPE_P
	
	private Date dateOfIssue;
	private Date dateOfExpiry;
	private String nantionality;
	private String placeOfIssue;
	private int docType;
	
	

	/**  
	 *  Factory method to create document
	 */
	public static TravelDocument createDocument(int docType) {
		//This stle was chosen to avoid class proiferation
		if(docType == BLConstants.TRAVEL_DOC_TYPE_PASSPORT)
			return new Passport();
		else if(docType == BLConstants.TRAVEL_DOC_TYPE_VISA)
			return new Visa();
		else if(docType == BLConstants.TRAVEL_DOC_TYPE_ALIEN_REG)
			return new AlienRegCard();
		else //TODO throw an exception
			return null; 
	}



	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getNantionality() {
		return nantionality;
	}

	public void setNantionality(String nantionality) {
		this.nantionality = nantionality;
	}

	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	public abstract Integer getType();
	
	
	
	
	
	/*
	public static final DocumentType VISA  = new DocumentType(1);
	
	//Doc type is an enum
	

}
