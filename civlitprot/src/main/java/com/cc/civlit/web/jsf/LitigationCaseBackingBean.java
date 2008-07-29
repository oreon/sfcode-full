package com.cc.civlit.web.jsf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.witchcraft.model.jsf.BaseBackingBean;

import com.cc.civlit.domain.CaseAdministrator;
import com.cc.civlit.domain.LitigationCase;

public class LitigationCaseBackingBean extends LitigationCaseBackingBeanBase
		implements java.io.Serializable {

	private List<CaseAdministrator> assignedLawyers = new ArrayList<CaseAdministrator>();
	
	private List<CaseAdministrator> availableLawyers = new ArrayList<CaseAdministrator>();
	
	CaseAdministratorBackingBean  caseAdminBean = (CaseAdministratorBackingBean)getBean("caseAdministratorCrudBacking");

	private static final Logger log = Logger
			.getLogger(LitigationCaseBackingBean.class);
	private static final long serialVersionUID = 1L;

	public List<CaseAdministrator> getAvailableLawyers() {
		List<CaseAdministrator> allAdmins =  caseAdminBean.getRecords();
		return ListUtils.subtract(allAdmins, assignedLawyers);
	}

	public void setAvailableLawyers(List<CaseAdministrator> availableLawyers) {
		this.availableLawyers = availableLawyers;
	}

	public String assignLawyers() {
		for (CaseAdministrator ca : assignedLawyers) {
			System.out.println("Assigned lawyer " + ca.getId());
		}
		return "success";
	}

	public List<CaseAdministrator> getAssignedLawyers() {
		return assignedLawyers;
	}

	public void setAssignedLawyers(List<CaseAdministrator> assignedLawyers) {
		this.assignedLawyers = assignedLawyers;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		super.reset();
		assignedLawyers.clear();
	}
	
	@Override
	public String update() {
		Set<CaseAdministrator> updatedAdimns = new HashSet<CaseAdministrator>();
		for (CaseAdministrator ca : assignedLawyers) {
			ca = caseAdminBean.getCaseAdministratorService().load(ca.getId());
			updatedAdimns.add(ca);
			System.out.println("Assigned lawyer " + ca.getFirstName());
		}
		reloadFromId(litigationCase.getId());
		litigationCase.setCaseAdministrator(updatedAdimns);
		return super.update();
	}
	
	protected void reloadFromId(long id) {
		if(id != 0)
			litigationCase = litigationCaseService.load(id);
		assignedLawyers.addAll(litigationCase.getCaseAdministrator()) ;
	}
}
