package com.oreon.smartsis.web.action.domain;

public class ScoreMetrics {
	
	private Integer rank;
	private Double average;
	private Long total;
	private Long max;
	private Double percentage;
	
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public void setMax(Long max) {
		this.max = max;
	}
	public Long getMax() {
		return max;
	}

}
