package org.caisi.persistence.base;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**This class represents a range 
 * @author jesing
 *
 * @param <T>
 */
public class Range<T> {
	private T begin;
	private T end;

	private boolean beginInclusive = true;
	private boolean endInclusive = true;
	private String propertyName;
	
	private static final Logger logger = Logger.getLogger(Range.class);
	
	public Range(){}
	
	public Range(String propertyName){
		this.propertyName = propertyName;
	}
	
	public Range(String propertyName,T begin, T end) {
		super();
		this.begin = begin;
		this.end = end;
		this.propertyName = propertyName;
	}

	public Range(T begin, boolean beginInclusive, T end, 
			boolean endInclusive, String propertyName) {
		super();
		this.begin = begin;
		this.end = end;
		this.beginInclusive = beginInclusive;
		this.endInclusive = endInclusive;
		this.propertyName = propertyName;
	}
	
	

	public T getBegin() {
		return begin;
	}

	public void setBegin(T begin) {
		this.begin = begin;
	}

	public T getEnd() {
		return end;
	}

	public void setEnd(T end) {
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
		if(begin == null && end == null){
			logger.info("both begin and end are null for " + propertyName);
			return; 
		}
		if (begin != null)
			criteria.add( beginInclusive ? Restrictions.ge(propertyName, begin)
					: Restrictions.gt(propertyName, begin) );
		if (end != null)
			criteria.add( endInclusive ? Restrictions.le(propertyName, end)
					: Restrictions.lt(propertyName, end) );
	}

	public void clear() {
		begin = null;
		end = null;
	}

}
