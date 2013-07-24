package com.oreon.cerebrum.web.action.charts;

import javax.persistence.EntityManager;

import org.testng.annotations.Test;

import com.oreon.cerebrum.charts.AppliedChart;
import com.oreon.cerebrum.charts.Chart;
import com.oreon.cerebrum.patient.Patient;

public class AppliedChartActionTest extends AppliedChartActionTestBase {

	@Test
	public void testCreateProcedures() throws Exception {

		EntityManager em = getEntityManagerFactory().createEntityManager();
		appliedChartAction.setEntityManager(em);
		em.getTransaction().begin();
		//em.
		
		
		Patient pt = em.find(Patient.class, 1L);
		Chart chart = em.find(Chart.class, 1L);
		
		appliedChartAction.setInstance(new AppliedChart());
		
		appliedChartAction.getAppliedChart().setPatient(pt);
		appliedChartAction.getAppliedChart().setChart(chart);
		
		appliedChartAction.createProcedures();
		
		em.close();
	

		/*
		new ComponentTest() {

			protected void testComponents() throws Exception {
				invokeMethod("#{appliedChartAction.appliedChart.setPatient(pt)}");
				invokeMethod("#{appliedChartAction.appliedChart.setChart(chart)}");
				invokeMethod("#{appliedChartAction.createProcedures}");
			}

		}.run();*/
	}

}
