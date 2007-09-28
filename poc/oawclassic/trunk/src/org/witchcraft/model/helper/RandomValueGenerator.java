package org.witchcraft.model.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import oaw4.demo.classic.uml.meta.Column;

import org.openarchitectureware.core.meta.core.Element;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Enumeration;

public class RandomValueGenerator {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
	
	static String randStrings[] = {"alpha", "beta", "gamma", "delta",  "theta", "zeta", "epsilon", "pi" };
	
	public static Object getRandomValue(Attribute attribute){
		
		String typeName = attribute.Type().NameS();
		Random generator = new Random(19580427);
		
		
		if(typeName.equals("String")){
			
			String uniqueNum = "";
			
			if(attribute instanceof Column){
				if( ((Column)attribute).isUnique() )
					uniqueNum = new Long(new Random().nextInt(100000)).toString();
			}
			return "\"" + randStrings[new Random().nextInt(randStrings.length)] + uniqueNum +  "\"" ;
		}else if (typeName.contains("Date")){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(new Date().getTime() - new Random().nextInt(100000) * 200000);
			
			return "dateFormat.parse(\"" + dateFormat.format(cal.getTime()) + "\")"; 
		}else if (typeName.equalsIgnoreCase("int") ){
			return (attribute.InitValue() == null )? new Random().nextInt(10000): attribute.InitValue();
		}else if (typeName.equalsIgnoreCase("double") || typeName.equalsIgnoreCase("BigDecimal") ){
			return (attribute.InitValue() == null )? 100 * generator.nextDouble(): attribute.InitValue();
		}else if (attribute.Type().getMetaClass().getSimpleName().equalsIgnoreCase("Enumeration")){
			ElementSet literals = ((Enumeration) attribute.Type()).Literal();
			Element element = (Element) literals.get(generator.nextInt(literals.size()));
			return attribute.Type().Name() + "." + element.NameS();	
		}
		
		System.out.println("attrib type " + attribute.Type().getMetaClass().getSimpleName());
		return "\"unknown attrib type\"";
		
	}

}
