package com.pcas.datapkg;

import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;

import org.witchcraft.base.entity.Range;

public class SerializationTester implements Serializable{
	
	private Range<BigDecimal> salaryRange = new Range<BigDecimal>();
	
	private String firstName;
	
	public static void main(String[] args) {
		
		SerializationTester st = new SerializationTester();
		st.getSalaryRange().setBegin(new BigDecimal(10000));
		st.setFirstName("jag");
		
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 20);
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(bos ));
		
		PersistenceDelegate pd=encoder.getPersistenceDelegate(Integer.class); 
		encoder.setPersistenceDelegate(BigDecimal.class,pd );

		encoder.writeObject(st);
		encoder.close();
		//System.out.println(" ecoded xml : " + new String(bos.toByteArray()));
		System.out.println (new String(bos.toByteArray()));
		
		
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setSalaryRange(Range<BigDecimal> salaryRange) {
		this.salaryRange = salaryRange;
	}

	public Range<BigDecimal> getSalaryRange() {
		return salaryRange;
	}

}
