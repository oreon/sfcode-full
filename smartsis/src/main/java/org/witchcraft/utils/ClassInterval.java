package org.witchcraft.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bsh.This;

public class ClassInterval {
	
	int intervalSize = 10;
	double maxValue = 100.0;
	
	private Map<Integer, Integer> intervals = new HashMap<Integer, Integer>();
	
	public ClassInterval(Double[] values){
		for (Double val : values) {
			addValue(val);
		}
	}
	
	public Map<Integer, Integer> getIntervalsMap(){
		return intervals;
	}
	
	public ClassInterval() {
		// TODO Auto-generated constructor stub
	}

	public void addValue(Double value){
		int num = (value.intValue() )/intervalSize;
		if(num == maxValue/intervalSize){
			num = num -1;
		}
		Integer currentCount = intervals.get(num);
		if(  currentCount == null )
			intervals.put(num, 1); 
		else
			intervals.put(num, currentCount + 1);
		 
	}
	
	public Map<String, Integer>  getIntervalCounts(){
		Set<Integer> keys = intervals.keySet();
		Map<String, Integer> retMap = new HashMap<String, Integer>();
		for (Integer key : keys) {
			String strkey = (key * intervalSize) + "-"  + (key +1 )*intervalSize;
			
			retMap.put(strkey, intervals.get(key));
		}
		return retMap;
	}
	
	
	public static void main(String[] args) {
		ClassInterval interval = new ClassInterval(new Double[]{33.0, 65.0, 67.1, 73.9 ,84.2,89.3,88.2, 92.1});
		Map<String, Integer> ret = interval.getIntervalCounts();
		Set<String> keys = ret.keySet();
		for (String key : keys) {
			System.out.println(key + " " + ret.get(key));
		}
	}
	

}
