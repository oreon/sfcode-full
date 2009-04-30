package org.caisi.persistence.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.caisi.persistence.base.exceptions.BusinessException;

/** Responsible for creating range objects of various types
 * @author jsingh
 *
 */
public class RangeFactory {
	
	public static Range<Date> createDateRange(HttpServletRequest request,String fldName, String fromFld, String toFld){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		simpleDateFormat.setLenient(true);
		String fromFldVal = request.getParameter(fromFld);
		String toFldVal = request.getParameter(toFld);
		Date from, to;
	
		try {
			from = (!StringUtils.isEmpty(fromFldVal) ) ? simpleDateFormat.parse(fromFldVal):null;
			to = (!StringUtils.isEmpty(toFldVal) ) ? simpleDateFormat.parse(toFldVal):null;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BusinessException("Dates should be in YYYY-mm-dd format");
		}
		
		return new Range<Date>( fldName, from, to);
	}
	
	

}
