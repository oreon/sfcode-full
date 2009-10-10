package org.caisi.persistence.base;

import org.caisi.persistence.model.Demographic;
import org.caisi.sessionbeans.DemographicSessionBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try{
        String[] contexts = {"classpath:/applicationContext.xml"
        		,"classpath:/persistenceContext.xml" };
        ApplicationContext context = new ClassPathXmlApplicationContext(contexts);
        DemographicSessionBean oscarDoa = (DemographicSessionBean)context.getBean("demographicDao");
        Demographic demographic = new Demographic();
        demographic.setFirstName("eric");
        demographic.setLastName("Segal22");
        oscarDoa.save(demographic);
       
        System.out.println(demographic.getDemographicNo());
        }catch(Exception e){
        	e.printStackTrace();
        }
        
    }
}
