
	
package com.oreon.cerebrum.web.action.patient;
	

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.witchcraft.exceptions.BusinessException;

import com.oreon.cerebrum.facility.Bed;
import com.oreon.cerebrum.facility.Room;
import com.oreon.cerebrum.facility.RoomType;
import com.oreon.cerebrum.facility.Ward;
import com.oreon.cerebrum.patient.BedStay;
import com.oreon.cerebrum.patient.Patient;
import com.oreon.cerebrum.web.action.facility.BedAction;

	
@Scope(ScopeType.CONVERSATION)
@Name("admissionAction")
public class AdmissionAction extends AdmissionActionBase implements java.io.Serializable{
	
	private Ward ward;
	
	private RoomType roomType;
	
	private Bed bed ;
	
	private Bed preferredBed ;
	
	private Bed nonPreferredBed ;
	
	@In
	Conversation conversation;
	
	public Bed getPreferredBed() {
		return preferredBed;
	}

	public void setPreferredBed(Bed preferredBed) {
		this.preferredBed = preferredBed;
	}

	
	
	
	public Bed getNonPreferredBed() {
		return nonPreferredBed;
	}

	public void setNonPreferredBed(Bed nonPreferredBed) {
		this.nonPreferredBed = nonPreferredBed;
	}

	private Patient patient;
	
	@In(create=true)
	BedAction bedAction;
	
	static String qryPref = "Select r from Room r where r.ward.id = ? and r.roomType = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	static String qryNonPref  = "Select r from Room r where r.ward.id = ? and r.roomType != ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	
	static String qryAll = "Select r from Room r where r.ward.id = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	
	public List<Room> getPreferredRoomsList(){
		if(ward == null || roomType == null)
			return new ArrayList<Room>();
		//String nativeQry = "SELECT * FROM Room r WHERE r.ward_id = ? AND ( SELECT COUNT(*) FROM Bed b WHERE b.patient_id IS NULL AND b.room_id = r.id  > 0 )";
		System.out.println("looking for " + ward.getName() + " " + roomType.getName());
		List<Room> rooms =  executeQuery(qryPref, ward.getId(), roomType );
		
		return rooms;
	}
	
	@Begin(join=true)
	public List<Bed> getPrefferedBeds(){
		
		System.out.println(conversation.getId());
		
		List<Room> rooms = getPreferredRoomsList();
		List<Bed> beds = new ArrayList<Bed>();
		for (Room room : rooms) {
			Hibernate.initialize(room.getBeds());
			beds.addAll(room.getBeds());
		}
		
		
		return beds;
	}
	
	@Begin(join=true)
	public List<Room> getNonPreferredRoomsList(){
		if(ward == null )
			return new ArrayList<Room>();
		
		if(roomType != null)
			return executeQuery(qryNonPref, ward.getId(), roomType);
		
		return executeQuery(qryAll, ward.getId());
	}
	
	public List<Bed> getNonPreferredBedsList (){
		List<Room> rooms = getNonPreferredRoomsList();
		List<Bed> beds = new ArrayList<Bed>();
		for (Room room : rooms) {
			Hibernate.initialize(room.getBeds());
			beds.addAll(room.getBeds());
		}
		return beds;
	}
	
	
	@Override
	public void updateComposedAssociations() {
		// TODO Auto-generated method stub
		//super.updateComposedAssociations();
	}
	
	@Override
	public boolean isDischargeEnabled(){
		return instance.getDischargeDate() == null;
	}
	
	@Override
	public boolean isTransferEnabled() {
		return instance.getDischargeDate() == null;
	}
	
	//@Override
	public void discharge(String dischargeNote,
			com.oreon.cerebrum.patient.DischargeCode dischargeCode) {
		
		updateBedStayDate();
		
		instance.setDischargeDate(new Date());
		super.save();
		addInfoMessage("Successfully Discharged patient" );

	}
	
	public void discharge(){
		discharge(instance.getDischargeNote(), instance.getDischargeCode());
	}

	private void updateBedStayDate() {
		for (BedStay bedStay : instance.getBedStays()) {
			if(bedStay.getToDate()  == null)
				bedStay.setToDate(new Date());
		}
	}
	
	@Override
	public void transfer() {
		
	
		updateBedStayDate();
		createBedStay();
		
		listBedStays.clear();
		listBedStays.addAll(getInstance().getBedStays());
		
		super.save();
		
		addInfoMessage("Successfully transferred to bed " + instance.getBed());
		
	}
	
	@Override
	@End
	public String save() {
		bed = preferredBed != null ? preferredBed : nonPreferredBed;
		
		if(bed == null )
			throw new BusinessException("Please Select a Bed");
		createBedStay();
		
		return super.save();
	}

	private void createBedStay() {
		//bed = instance.getBed();
		bed.setPatient(instance.getPatient());
			
		BedStay bedStay = new BedStay();
		bedStay.setAdmission(instance);
		bedStay.setBed(bed);
		bedStay.setFromDate(new Date());
		instance.addBedStay(bedStay);
	}
	


	public void setWard(Ward ward) {
		this.ward = ward;
	}


	public Ward getWard() {
		return ward;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}


	public RoomType getRoomType() {
		return roomType;
	}


	public void setBed(Bed bed) {
		this.bed = bed;
	}


	public Bed getBed() {
		return bed;
	}

	@Begin(join=true)
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}
	
	public void updateSelectedBed(){
		//this.selectedBed = this.bed;
	}
	
}
	