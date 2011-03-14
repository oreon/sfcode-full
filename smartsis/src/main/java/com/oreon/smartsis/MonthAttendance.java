package com.oreon.smartsis;

public class MonthAttendance {
	
	String month;
	Long	count;
	Long total; 
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	public static final String[] ARR_MONTH = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG","SEP","OCT","NOV","DEC"};
	
	public MonthAttendance() {
		
	}
	
	public MonthAttendance(Integer month, Long count, Long total) {
		super();
		this.month = ARR_MONTH[month - 1];
		this.count = count;
		this.total = total;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
	

}
