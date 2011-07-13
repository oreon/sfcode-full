
	
package com.oreon.smartsis.web.action.domain;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.oreon.smartsis.attendance.Attendance;
import com.oreon.smartsis.domain.Grade;
import com.oreon.smartsis.domain.PaidFee;
import com.oreon.smartsis.domain.Student;

	
//@Scope(ScopeType.CONVERSATION)
@Name("gradeFeesInstanceAction")
public class GradeFeesInstanceAction extends GradeFeesInstanceActionBase implements java.io.Serializable{

	@Override
	public void prePopulateListPaidFees(){
		if (getInstance().getId() == null && listPaidFees.isEmpty()) {
			Grade grade = getInstance().getGrade();
			if (grade == null)
				return;
			Set<Student> students = grade.getStudents();
			
			for (Student student : students) {
				PaidFee paidFee = new PaidFee();
				paidFee.setGradeFeesInstance(getInstance());
				paidFee.setStudent(student);
				//paidFee.setDate(getInstance().getDate());
				listPaidFees.add(paidFee);
			}
		}
	
	}
	
}

	