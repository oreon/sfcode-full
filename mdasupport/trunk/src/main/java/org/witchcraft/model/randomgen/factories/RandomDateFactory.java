package org.witchcraft.model.randomgen.factories;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomValueCreator;

public class RandomDateFactory implements RandomValueCreator<Date>{
	
	public Date createOne(){
		GregorianCalendar cal = new GregorianCalendar();
		//cal.set(Calendar.YEAR, )
		cal.setTimeInMillis(new Date().getTime() - new Random().nextInt(100000)
				* 200000);
		return cal.getTime(); 
	}

	public Date createOne(Collection<Date> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date createOne(Date low, Date high) {
		// TODO Auto-generated method stub
		return null;
	}

}
