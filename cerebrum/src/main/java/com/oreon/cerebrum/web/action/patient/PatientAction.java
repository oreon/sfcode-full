package com.oreon.cerebrum.web.action.patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.seam.annotations.Name;
import org.joda.time.JodaTimePermission;

import com.oreon.cerebrum.charts.AppliedChart;
import com.oreon.cerebrum.charts.ChartItem;
import com.oreon.cerebrum.charts.ChartProcedure;
import com.oreon.cerebrum.charts.TimeEnumeration;
import com.oreon.cerebrum.encounter.Encounter;
import com.oreon.cerebrum.patient.TrackedVital;
import com.oreon.cerebrum.patient.VitalValue;

//@Scope(ScopeType.CONVERSATION)
@Name("patientAction")
public class PatientAction extends PatientActionBase implements
		java.io.Serializable {

	private ArrayList<BloodPressure> bpList;

	public  PatientAction() {
		// TODO Auto-generated constructor stub

	}
	
	/**
	 * Should calculate upcoming chart procedure dates 
	 */
	public void viewUpcomingChartProcedures(){
		
	
		Set<AppliedChart> charts = instance.getAppliedCharts();
		
		List<ChartProcedure> procedures = new ArrayList<ChartProcedure>();
		
		
		for (AppliedChart appliedChart : charts) {
			Date beginDate = appliedChart.getDateCreated();
			
			Set<ChartItem> items = appliedChart.getChart().getChartItems();
			for (ChartItem chartItem : items) {
				TimeEnumeration frequency = chartItem.getFrequencyPeriod();
				int duration = chartItem.getDuration();
				if(frequency == TimeEnumeration.WEEK){
					ChartProcedure procedure = new ChartProcedure();
					//procedure.setDueDate( duration * ProjectUtils.getTimeFor)
					//procedures.add(new ChartProcedure())
				}
				//JodaTimePermission
			}
			
		}
	}
	
	
	public void initBloodPressureValues(){
		
		if(instance == null )
			load(0L);
		
		bpList = new ArrayList<BloodPressure>();
		
		
		Set<Encounter> encounters = getInstance().getEncounters();
		for (Encounter encounter : encounters) {
			if(encounter.getVitals() != null  && encounter.getVitals().getSysBP() != null && encounter.getVitals().getDiasBP() != null)
				bpList.add(new BloodPressure(encounter.getDateCreated(), encounter.getVitals().getSysBP(), encounter.getVitals().getDiasBP()));
		}
	}

	public List<BloodPressure> getBloodPressureValues() {
		if(bpList == null)
			initBloodPressureValues();
		return bpList;
	}

	public List<List<VitalValue>> getTrackedVals(){
		List<List<VitalValue>>  listVitals = new ArrayList<List<VitalValue>>();
		Map<TrackedVital, List<VitalValue>> mapVitals = new HashMap<TrackedVital, List<VitalValue>>();
		
		Set<VitalValue> vitals = getInstance().getVitalValues();
		for (VitalValue vitalValue : vitals) {
			if(!mapVitals.containsKey(vitalValue.getTrackedVital())){
				mapVitals.put(vitalValue.getTrackedVital(), new ArrayList<VitalValue>());
			}
			mapVitals.get(vitalValue.getTrackedVital()).add(vitalValue);
		}
		Set<TrackedVital>  tvs = mapVitals.keySet();
		for (TrackedVital trackedVital : tvs) {
			listVitals.add(mapVitals.get(trackedVital));
		}
		return listVitals;
	}
	
	class DateComparator implements Comparator<BloodPressure>{

		@Override
		public int compare(BloodPressure bp1, BloodPressure bp2) {

			if(bp1.getDate().getTime() > bp2.getDate().getTime())
				return 1;
			return 0;
		}
		
	}
	
}
