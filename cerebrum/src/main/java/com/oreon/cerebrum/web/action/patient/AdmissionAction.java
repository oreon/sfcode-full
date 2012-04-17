
	
package com.oreon.cerebrum.web.action.patient;
	

import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.facility.Bed;
import com.oreon.cerebrum.facility.Room;
import com.oreon.cerebrum.facility.RoomType;
import com.oreon.cerebrum.facility.Ward;
import com.oreon.cerebrum.patient.BedStay;
import com.oreon.cerebrum.web.action.facility.BedAction;

	
//@Scope(ScopeType.CONVERSATION)
@Name("admissionAction")
public class AdmissionAction extends AdmissionActionBase implements java.io.Serializable{
	
	private Ward ward;
	
	private RoomType roomType;
	
	private Bed bed;
	
	@In(create=true)
	BedAction bedAction;
	
	static String qryPref = "Select r from Room r where r.ward.id = ? and r.roomType = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	static String qryNonPref  = "Select r from Room r where r.ward.id = ? and r.roomType != ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	
	static String qryAll = "Select r from Room r where r.ward.id = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	
	public List<Room> getPreferredRoomsList(){
		if(ward == null || roomType == null)
			return null;
		//String nativeQry = "SELECT * FROM Room r WHERE r.ward_id = ? AND ( SELECT COUNT(*) FROM Bed b WHERE b.patient_id IS NULL AND b.room_id = r.id  > 0 )";
		
		List<Room> rooms =  executeQuery(qryPref, ward.getId(), roomType );
		
		return rooms;
	}
	
	public List<Room> getNonPreferredRoomsList(){
		if(ward == null )
			return null;
		
		if(roomType != null)
			return executeQuery(qryNonPref, ward.getId(), roomType);
		
		return executeQuery(qryAll, ward.getId());
	}
	
	@Override
	public String save() {
		bed.setPatient(instance.getPatient());
		//bedAction.persist(bed);
		
		BedStay bedStay = new BedStay();
		bedStay.setAdmission(instance);
		bedStay.setFromDate(new Date());
		
		instance.addBedStay(bedStay);
		
		return super.save();
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
	
}
	