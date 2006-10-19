package aero.sita.ads.framework.apis2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aero.sita.ads.framework.apis2.traveldocs.TravelDocument;

/**
 * This class contains all the apisInfo components 
 * @author jsingh
 *
 */
public class APISInfo {
	
	private Map travelDocuments = new HashMap();
	
	private DestinationInfo destinationInfo = new DestinationInfo();
	private PersonalInfo personalInfo = new PersonalInfo();
	
	public void addTravelDocument(TravelDocument document){
		travelDocuments.put(document.getType(), document);
	}
	
	/**
	 * @param docType - the type of document ie visa, passport, aline reg card etc
	 * If the document is not already in the map this function will add it
	 * @return
	 */
	public TravelDocument getTravelDocument(int docType){
		TravelDocument document = (TravelDocument)travelDocuments.get(new Integer(docType));
		if(document == null){
			document = TravelDocument.createDocument(docType);
			addTravelDocument(document);
		}

		return document;
	}

	public DestinationInfo getDestinationInfo() {
		return destinationInfo;
	}

	public void setDestinationInfo(DestinationInfo destinationInfo) {
		this.destinationInfo = destinationInfo;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	/*
	public List getTravelDocuments() {
		return travelDocuments;
	}

	public void setTravelDocuments(List travelDocuments) {
		this.travelDocuments = travelDocuments;
	}*/
	
	/**convenience method
	 * @returns if any of the components have a missing info
	 */
	public boolean isMissingInfo() {
		return getMissingFields().size() > 0;
	}

	/**
	 * @return all the missing fields
	 */
	private Map getMissingFields() {
		Map missingFields  = new HashMap();;
		missingFields.putAll(destinationInfo.getMissingFields());
		missingFields.putAll(personalInfo.getMissingFields());
		
		for(Iterator iter = travelDocuments.keySet().iterator(); iter.hasNext();  ){
			missingFields.putAll( ((TravelDocument)travelDocuments.get( iter.next())).getMissingFields() );
		}
		
		return missingFields;
	}
	
	/** 
	 * @return all the  components as a collection 
	 */
	public Set getAPISInfoComponents(){
		Set set = new HashSet();
		
		
		set.addAll(travelDocuments.values());
		set.add(personalInfo);
		set.add(destinationInfo);
		return set;
	}

	
	/**
	 * @return All missing components as a collection
	 */
	public Set getMissingAPISInfoComponents() {
		
		Set componentsWithMissingFields = new HashSet();
		
		for(Iterator iter = getAPISInfoComponents().iterator(); iter.hasNext();){
			APISInfoComponent component = (APISInfoComponent)iter.next();
			if( component.getMissingFields().size() > 0 )
				componentsWithMissingFields.add(component);
		}
		
		return componentsWithMissingFields;
	}
	
	
	
}
