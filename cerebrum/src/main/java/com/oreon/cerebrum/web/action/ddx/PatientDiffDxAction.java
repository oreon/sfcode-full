package com.oreon.cerebrum.web.action.ddx;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
	
	List<String> differentials = new ArrayList<String>();

	public List<String> getDifferentials() {
		return differentials;
	}

	public void setDifferentials(List<String> differentials) {
		this.differentials = differentials;
	}

	public String addFinding(Finding finding) {

		finding.getDifferentialDxs();
		StringBuilder warnings = new StringBuilder();

		List<PatientFinding> items = getListPatientFindings();

		// TODO: search for patinetFinding.finding
		// if (items.contains(finding))
		// return "nothing";

		// for (PatientFinding patientFinding : items) {
		List<DifferentialDx> dxs = finding.getListDifferentialDxs();
		
		for (DifferentialDx differentialDx : dxs) {
			map.putIfAbsent(differentialDx.getName(), new AtomicLong(0));
			map.get(differentialDx.getName()).incrementAndGet();
		}
		// }

		updateDiff();
		
		return "success";
	}

	// @Override
	public void setNewFinding(Finding finding) {
		addFinding(finding);
		// this.finding = finding;
	}
	
	public List<String> updateDiff(){
		ValueComparator comp = new ValueComparator(map);
		TreeMap<String, AtomicLong> sorted = new TreeMap(comp);
		sorted.putAll(map);
		
		Set<String> keys = sorted.keySet();
		
		for (String key : keys) {
			differentials.add(key + " " + sorted.get(key));
			System.out.println(key + " " + sorted.get(key));
		}
		
		return differentials;
	
	}

	class ValueComparator implements Comparator {

		Map base;

		public ValueComparator(Map base) {
			this.base = base;
		}

		public int compare(Object a, Object b) {
			
			Long val1 = ((AtomicLong) base.get(a)).longValue();
			Long val2 = ((AtomicLong) base.get(b)).longValue();
			
			//descending compare
			return val2.compareTo(val1);
			
		}
	}

}
