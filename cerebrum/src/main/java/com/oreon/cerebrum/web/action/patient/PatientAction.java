package com.oreon.cerebrum.web.action.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.joda.time.DateTime;

//@Scope(ScopeType.CONVERSATION)
@Name("patientAction")
public class PatientAction extends PatientActionBase implements
		java.io.Serializable {

	private List<Birth> births;

	public  PatientAction() {
		// TODO Auto-generated constructor stub

	}
	
	public void initBirths(){
		
		births = new ArrayList<Birth>();
		births.add(new Birth(new Date(), 120, 52));
		births.add(new Birth(new DateTime(2012, 5, 6, 6, 6, 7, 6).toDate(), 100, 60));
		births.add(new Birth(new DateTime(2012, 5, 8, 6, 6, 7, 6).toDate(), 44, 110));
		births.add(new Birth(new DateTime(2012, 5, 9, 6, 6, 7, 6).toDate(), 150, 135));
		births.add(new Birth(new DateTime(2012, 5, 22, 6, 6, 7, 6).toDate(), 125, 120));
	}

	public List<Birth> getBirths() {
		if(births == null)
			initBirths();
		return births;
	}

	
	
}
