package oaw4.demo.classic.uml.extend;

import java.io.StringWriter;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class VelocityTemplateMerger {
	
	private static final Logger log = Logger.getLogger(VelocityTemplateMerger.class);
	
	static{
		try {
			Velocity.init();
		} catch (Exception e) {
			log.fatal("Veloicity initialization failed " , e);
			e.printStackTrace();
		}
	}
	
	public static String merge(VelocityContext context, String templateFile){

		Template template = null;

		try {
			template = Velocity.getTemplate(templateFile);
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			return sw.getBuffer().toString();
			
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
		} catch (ParseErrorException pee) {
			pee.printStackTrace();
		} catch (MethodInvocationException mie) {
			mie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
