
	
package com.oreon.smartsis.web.action.exam;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

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
import org.witchcraft.utils.ClassInterval;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

	
//@Scope(ScopeType.CONVERSATION)
@Name("electronicExamEventAction")
public class ElectronicExamEventAction extends ElectronicExamEventActionBase implements java.io.Serializable{
	
	private Map<String, Double> summaryStats =  null;
	private Map<Integer, Integer> mapIntervals = null;
	
	public void updateStats() {
		
		summaryStats =  new HashMap<String, Double>();
		mapIntervals = new HashMap<Integer, Integer>();
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		String qry = "select e.score from ElectronicExamInstance e where e.electronicExamEvent.id = ?1 ";
		List<Integer> scores = executeQuery(qry, getInstance().getId());
		
		ClassInterval ci = new ClassInterval();
		
		

		for (Integer score : scores) {
			double scorePerc = 100.0 * score.doubleValue()/getInstance().getElectronicExam().getQuestionsCount();
			ci.addValue(scorePerc);
			stats.addValue(score);
		}

		summaryStats.put("High", stats.getMax());
		summaryStats.put("Low", stats.getMin());
		summaryStats.put("Mean", stats.getMean());
		summaryStats.put("Median", stats.getPercentile(50)); // median
		summaryStats.put("Std Deviation", stats.getStandardDeviation());
		summaryStats.put("Variance", stats.getVariance());
		summaryStats.put("Kurtosis", stats.getKurtosis());
		summaryStats.put("Geometric Mean", stats.getGeometricMean());
		
		mapIntervals = ci.getIntervalsMap();
	}

	public Map<String, Double> getSummaryStats() {
		if(summaryStats == null){
			updateStats();
		}
		return summaryStats;
	}

	public void setSummaryStats(Map<String, Double> summaryStats) {
		this.summaryStats = summaryStats;
	}

	public Map<Integer, Integer> getMapIntervals() {
		if(mapIntervals == null){
			updateStats();
		}
		return mapIntervals;
	}

	public void setMapIntervals(Map<Integer, Integer> mapIntervals) {
		this.mapIntervals = mapIntervals;
	}
}
	