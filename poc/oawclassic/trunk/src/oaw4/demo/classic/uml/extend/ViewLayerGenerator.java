package oaw4.demo.classic.uml.extend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.core.meta.visitor.ModelElementVisitor;
import org.openarchitectureware.core.meta.visitor.TypeCollectingVisitor;
import org.openarchitectureware.meta.uml.ModelElement;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.StateMachine;
import org.openarchitectureware.meta.uml.state.Transition;

/**
 * To generate a basic view layer
 * 
 * @author jsingh
 * 
 */
public class ViewLayerGenerator {

	// String templateFlie = "templates/velocity/view/xhtm.vm";
	static String templateFile = "templates/velocity/view/jsfHtml.vm";

	public static String getView(Class cls) {
		String template= "templates/velocity/view/jsf/entityCreation.vm";
		VelocityContext context = createVelocityContext(cls);
		return VelocityTemplateMerger.merge(context, template);
	}

	private static VelocityContext createVelocityContext(Class cls) {
		VelocityContext context = new VelocityContext();
		context.put("clazz", cls);
		context.put("manager", new ViewLayerGenerator());
		context.put("utils", new ClassUtil());
		return context;
	}
	
	public static String getList(Class cls) {
		String template= "templates/velocity/view/jsf/entityList.vm";
		VelocityContext context = createVelocityContext(cls);
		return VelocityTemplateMerger.merge(context, template);
	}

	/**
	 * @returns a collection of the embeddable components of a given class
	 */
	public Map<String, Class> getComponents(Class cls) {

		Map<String, Class> components = new HashMap<String, Class>();

		for (Iterator iter = cls.AssociationEnd().iterator(); iter.hasNext();) {
			AssociationEnd ae = (AssociationEnd) iter.next();
			if (StereoTypeManager.isEmbeddable(ae.Opposite().Class()))
				components.put(
						ae.Opposite().NameS(), ae.Opposite().Class());
		}

		return components;
	}

	/**
	 * @returns a collection of the all superclasses of the given class
	 */
	public Set<Class> getSuperClasses(Class cls) {

		Set<Class> superClasses = new HashSet<Class>();

		Class tempClass = cls;

		// cls.NameS().

		while (tempClass.hasSuperClass()) {
			superClasses.add(tempClass.SuperClass());
			tempClass = tempClass.SuperClass();
		}

		return superClasses;
	}
	
	public static ElementSet getStates(StateMachine stateMachine){
		ModelElementVisitor visitor = new TypeCollectingVisitor(stateMachine, State.class);
		//stateMachine.visit(visitor);
		ElementSet states = ((TypeCollectingVisitor)visitor).getCollectedElements();
		return states;
	}
	
	public static String generateJspFromStateMachine(StateMachine stateMachine){
				
		String template= "templates/velocity/view/jsf/pageFlow.vm";
		
		VelocityContext context = new VelocityContext();
		context.put("states", getStates(stateMachine));
		String output =  VelocityTemplateMerger.merge(context, template);
		//System.out.println("GENERATEING STATEMACHINE :" +  output);
		
		return output;
	}
	
	/** This method will create page flow xml from the given state machine
	 * @param stateMachine
	 * @return
	 */
	public static String createStateMachine(StateMachine stateMachine){
				
		String template= "templates/velocity/view/jsf/pageFlow.vm";
		
		VelocityContext context = new VelocityContext();
		context.put("states", getStates(stateMachine));
		String output =  VelocityTemplateMerger.merge(context, template);
		
		return output;
	}
	
	private static void dumpSet(ElementSet set){
		for (Object object : set) {
			Transition transition = (Transition)object;
			//System.out.println( ((ModelElement)object).NameS());
			System.out.println( transition.NameS() + "-> " +  transition.TargetVertex().NameS());
		}
		System.out.println("-------------------------------------");
	}

}
