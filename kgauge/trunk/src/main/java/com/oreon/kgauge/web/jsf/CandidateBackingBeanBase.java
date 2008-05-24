package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.jsfbackingbeans.AuthenticationController;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.service.CandidateService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class CandidateBackingBeanBase extends BaseBackingBean<Candidate> {

	protected Candidate candidate = new Candidate();
	
	protected Candidate loggedinCandidate;

	protected CandidateService candidateService;

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Date> rangeDateOfBirth = new Range<Date>("dateOfBirth");

	public Range<Date> getRangeDateOfBirth() {
		return rangeDateOfBirth;
	}

	public void setRangeDateOfBirth(Range<Date> rangeDateOfBirth) {
		this.rangeDateOfBirth = rangeDateOfBirth;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void set(Candidate candidate) {
		this.candidate = candidate;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Candidate> getBaseService() {
		return candidateService;
	}

	public Candidate getEntity() {
		return getCandidate();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		candidate = new Candidate();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeDateOfBirth);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		candidate = candidateService.load(id);
		repeatPassword = candidate.getUser().getPassword();
	}

	public Candidate getLoggedinCandidate() {
		if(loggedinCandidate == null){
			AuthenticationController authenticationController = getBean("authenticationController");
			loggedinCandidate = candidateService.findByUsername(authenticationController.getUsername() );
		}
		
		return loggedinCandidate;
	}

	public void setLoggedinCandidate(Candidate loggedinCandidate) {
		this.loggedinCandidate = loggedinCandidate;
	}
	
	

}
