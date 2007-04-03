package oaw4.demo.classic.uml.extend;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Class;

/**
 * To generate a basic view layer
 * @author jsingh
 *
 */
public class ViewLayerGenerator {
	
	public static String getView(Class cls){
		
		VelocityContext context = new VelocityContext();
		context.put("clazz", cls);
		context.put("manager", new ViewLayerGenerator());
		context.put("utils", new ClassUtil());
		return VelocityTemplateMerger.merge(context, "templates/velocity/view/xhtm.vm");
	}
	
	/**
	 * @returns a collection of the embeddable components of a given class
	 */
	public Set<Class> getComponents(Class cls){
		
		Set<Class> components = new HashSet<Class>();
		
		for(Iterator iter = cls.AssociationEnd().iterator(); iter.hasNext(); ){
			AssociationEnd ae = (AssociationEnd) iter.next();
			if(StereoTypeManager.isEmbeddable(ae.Opposite().Class()) )
				components.add(ae.Opposite().Class());
		}
		
		return components;
	}
	
	/**
	 * @returns a collection of the all superclasses of the given class
	 */
	public Set<Class> getSuperClasses(Class cls){
		
		Set<Class> superClasses = new HashSet<Class>();
		
		Class tempClass = cls;
		
		//cls.NameS().
		
		while(tempClass.hasSuperClass()){
			superClasses.add(tempClass.SuperClass());
			tempClass = tempClass.SuperClass();
		}
		
		return superClasses;
	}
	

	
}
