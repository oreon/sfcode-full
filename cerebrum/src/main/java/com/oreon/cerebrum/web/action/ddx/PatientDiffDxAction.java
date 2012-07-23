package com.oreon.cerebrum.web.action.ddx;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.ddx.DifferentialDx;
import com.oreon.cerebrum.ddx.Finding;
import com.oreon.cerebrum.ddx.PatientFinding;

//@Scope(ScopeType.CONVERSATION)
@Name("patientDiffDxAction")
public class PatientDiffDxAction extends PatientDiffDxActionBase implements
		java.io.Serializable {
	
	final ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();

	public String addFinding(Finding finding) {

		finding.getDifferentialDxs();
		StringBuilder warnings = new StringBuilder();

		List<PatientFinding> items = getListPatientFindings();
		
		
	    

		//TODO: search for patinetFinding.finding
		//if (items.contains(finding))
		//	return "nothing";

		for (PatientFinding patientFinding : items) {
			List<DifferentialDx> dxs = patientFinding.getFinding().getListDifferentialDxs();
			for (DifferentialDx differentialDx : dxs) {
				map.putIfAbsent(differentialDx.getName(), new AtomicLong(0));
			    map.get(differentialDx.getName()).incrementAndGet();
			}
		}

		if (!StringUtils.isEmpty(warnings.toString())) {
			addErrorMessage(warnings.toString());
			System.out.println(warnings.toString());
		}
		return "success";
	}

	// @Override
	public void setNewFinding(Finding finding) {
		addFinding(finding);
		// this.finding = finding;
	}

}
