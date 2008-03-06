package org.witchcraft.model.support;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class Range {
	private Object begin;
	private Object end;

	private boolean beginInclusive = true;
	private boolean endInclusive = true;

	private String propertyName;
	
	public Range(){}

	public Range(Object begin, boolean beginInclusive, Object end, 
			boolean endInclusive, String propertyName) {
		super();
		this.begin = begin;
		this.end = end;
		this.beginInclusive = beginInclusive;
		this.endInclusive = endInclusive;
		this.propertyName = propertyName;
	}
	
	

	public Object getBegin() {
		return begin;
	}

	public void setBegin(Object begin) {
		this.begin = begin;
	}

	public Object getEnd() {
		return end;
	}

	public void setEnd(Object end) {
		this.end = end;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/** This method takes a criteria and add the current range's parameters to it
	 * @param criteria
	 */
	public void updateCriterion(Criteria criteria) {
		if(begin == null && end == null)
			return; 
		
		if (begin != null)
			criteria.add( beginInclusive ? Restrictions.ge(propertyName, begin)
					: Restrictions.gt(propertyName, begin) );
		if (end != null)
			criteria.add( endInclusive ? Restrictions.le(propertyName, end)
					: Restrictions.lt(propertyName, end) );
	}

}
