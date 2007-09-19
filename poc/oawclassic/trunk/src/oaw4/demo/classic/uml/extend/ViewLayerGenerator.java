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
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.StateMachine;
import org.openarchitectureware.meta.uml.state.Transition;
import org.witchcraft.htmlinput.jsf.InputComponentFactory;

/**
 * To generate a basic view layer
 * 
 * @author jsingh
 * 
 */
/**
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
	
	public static String getSearchPage(Class cls) {
		String template= "templates/velocity/view/jsf/entitySearch.vm";
		VelocityContext context = createVelocityContext(cls);
		return VelocityTemplateMerger.merge(context, template);
	}

	/**
	 * @returns a collection of the embeddable components of a given class
	 */
	public Map getComponents(Class cls) {

		Map<String, Class> components = new HashMap<String, Class>();
		
		Map<String, Attribute> associations = new HashMap<String, Attribute>();

		for (Iterator iter = cls.AssociationEnd().iterator(); iter.hasNext();) {
			AssociationEnd ae = (AssociationEnd) iter.next();
			//if embeddable we need all the attributes of the contained class
			if (StereoTypeManager.isEmbeddable(ae.Opposite().Class()))
				components.put(ae.Opposite().NameS(), ae.Opposite().Class());
			else if ( ae.Opposite().MultiplicityMaxAsInt() == 1 && ae.Opposite().NameS() != null ) {
				System.out.println("Adding assoc for class " + cls.NameS() + "::"  + ae.NameS() + "--" + ae.Opposite().Name());
				associations.put( ae.Opposite().NameS(), (Attribute) ae.Opposite().Class().Attribute().get(0));
			}
		}
		
		Map retMaps = new HashMap(); //<K, V>
		retMaps.put("components", components);
		retMaps.put("associations", associations);
		return retMaps;
	}
	
	Attribute getIdAttribute(Class cls){
		ElementSet attributes = cls.Attribute();
		for ( Object object : attributes) {
			Attribute attribute = (Attribute)object;
			if(attribute.Name().equals("id") )
				return attribute;
		}
		System.out.println("No id declared for this entity");
		return null;
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
	
	public static String getInputComponentContent(Attribute attribute){
		return InputComponentFactory.getRenderer(attribute).getContent(attribute);
	}
	
	public static String getInputComponentType(Attribute attribute){
		return InputComponentFactory.getRenderer(attribute).getType(attribute);
	}
	
	/** Get the attributes for this component type
	 * @param attribute
	 * @return
	 */
	public static String getInputComponentAttributes(Attribute attribute){
		return InputComponentFactory.getRenderer(attribute).getAttributes(attribute);
	}
	

}
