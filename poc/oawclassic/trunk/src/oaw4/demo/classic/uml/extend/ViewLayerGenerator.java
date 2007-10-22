package oaw4.demo.classic.uml.extend;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.core.meta.visitor.ModelElementVisitor;
import org.openarchitectureware.core.meta.visitor.TypeCollectingVisitor;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.StateMachine;
import org.openarchitectureware.meta.uml.state.Transition;
import org.witchcraft.htmlinput.jsf.InputComponentFactory;
import org.witchcraft.htmlinput.jsf.InputComponentRenderer;
import org.witchcraft.htmlinput.jsf.RenderContext;

import oaw4.demo.classic.uml.meta.Entity;

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
		String template = "templates/velocity/view/jsf/entityCreation.vm";
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
		String template = "templates/velocity/view/jsf/entityList.vm";
		VelocityContext context = createVelocityContext(cls);
		return VelocityTemplateMerger.merge(context, template);
	}

	public static String getSearchPage(Class cls) {
		String template = "templates/velocity/view/jsf/entitySearch.vm";
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
			// if embeddable we need all the attributes of the contained class
			if (StereoTypeManager.isEmbeddable(ae.Opposite().Class()))
				components.put(ae.Opposite().NameS(), ae.Opposite().Class());
			else if (ae.Opposite().MultiplicityMaxAsInt() == 1
					&& ae.Opposite().NameS() != null) {
				System.out.println("Adding assoc for class " + cls.NameS()
						+ "::" + ae.NameS() + "--" + ae.Opposite().Name());
				associations.put(ae.Opposite().NameS(), (Attribute) ae
						.Opposite().Class().Attribute().get(0));
			}
		}

		Map retMaps = new HashMap(); // <K, V>
		retMaps.put("components", components);
		retMaps.put("associations", associations);
		return retMaps;
	}

	Attribute getIdAttribute(Class cls) {
		ElementSet attributes = cls.Attribute();
		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;
			if (attribute.Name().equals("id"))
				return attribute;
		}
		System.out.println("No id declared for this entity");
		return null;
	}

	public static ElementSet getStates(StateMachine stateMachine) {
		ModelElementVisitor visitor = new TypeCollectingVisitor(stateMachine,
				State.class);
		// stateMachine.visit(visitor);
		ElementSet states = ((TypeCollectingVisitor) visitor)
				.getCollectedElements();
		
		Transition t;
		State s;
		
		//t.Action().
		//t.Trigger().
		
		return states;
	}
	
	/** For all states that have even one guard - we need to generate backing beans - this 
	 * function gets a list of such states
	 * @param stateMachine
	 * @return
	 */
	public static ElementSet getStatesWithGuards(StateMachine stateMachine) {
		ElementSet statesWithGuards = new ElementSet();
		ElementSet states = getStates(stateMachine);
		
		for (Object object : states) {
			State state = (State)object;
			for ( Object trans: state.OutTransition()){
				Transition transition = (Transition)trans;
				if(transition == null)
					continue;
				String guard = transition.Guard();
				if(guard != null && !StringUtils.EMPTY.equals(transition.Guard())){
					if(guard.contains(".")){//this guard is using a crud backing bean
						System.out.println("Gurard uses backing bean");
					}else{
						System.out.println(" adding transition with guard " + transition.Guard());
						statesWithGuards.add(state);
						break;
					}
				}
					
			}
		}
		
		return statesWithGuards;
	}

	public static String generateJspFromStateMachine(StateMachine stateMachine) {

		String template = "templates/velocity/view/jsf/pageFlow.vm";

		VelocityContext context = new VelocityContext();
		context.put("states", getStates(stateMachine));
		String output = VelocityTemplateMerger.merge(context, template);
		// System.out.println("GENERATEING STATEMACHINE :" + output);

		return output;
	}

	/**
	 * This method will create page flow xml from the given state machine
	 * 
	 * @param stateMachine
	 * @return
	 */
	public static String createStateMachine(StateMachine stateMachine) {

		String template = "templates/velocity/view/jsf/pageFlow.vm";

		VelocityContext context = new VelocityContext();
		context.put("states", getStates(stateMachine));
		String output = VelocityTemplateMerger.merge(context, template);

		return output;
	}

	private static void dumpSet(ElementSet set) {
		for (Object object : set) {
			Transition transition = (Transition) object;
			// System.out.println( ((ModelElement)object).NameS());
			System.out.println(transition.NameS() + "-> "
					+ transition.TargetVertex().NameS());
		}
		System.out.println("-------------------------------------");
	}

	public static InputComponentRenderer getInputComponent(Attribute attribute,
			RenderContext renderContext) {
		return InputComponentFactory.getRenderer(attribute, renderContext);
	}

	public static String getElementContent(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute,
				createCreateContext()).getContent(attribute);
	}

	public static String getElementType(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute,
				createCreateContext()).getType(attribute);
	}

	public static String getElementAttributes(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute,
				createCreateContext()).getAttributes(attribute);
	}

	public static boolean getElementRequired(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute,
				createCreateContext()).isRequired(attribute);
	}

	public static RenderContext createSearchContext() {
		return RenderContext.Search;
	}

	public static RenderContext createCreateContext() {
		return RenderContext.Create;
	}

	/** Get the left nav entities 
	 * @return
	 */
	public static String getEntitiesLeftNavMenu() {
		List<Entity> entites = ClassUtil.getEntities();
		StringBuffer data = new StringBuffer();

		String prevNameSpace = null;

		for (Entity entity : entites) {

			if (!entity.Namespace().NameS().equals(prevNameSpace)) {
				// Check if a prev namespace just ended
				if (prevNameSpace != null)
					data.append("</rich:panelMenuGroup>");
				data.append("<rich:panelMenuGroup label=\""
						+ getImmediatePackage(entity.Namespace().NameS())
						+ "\">");
			}

			data.append(" <rich:panelMenuItem><h:outputLink value=\""
					+ entity.NameS() + "List.jsf\">");
			data.append("<h:outputText value=\"" + entity.NameS()
					+ "\" /></h:outputLink></rich:panelMenuItem>");

			prevNameSpace = entity.Namespace().NameS();
		}
	
		if (!entites.isEmpty())
			data.append("</rich:panelMenuGroup>");
	
		
		return data.toString();
	}

	

	/**
	 * returns the immediate parent package i.e for org.withchraft.model.dao it
	 * will return dao
	 * 
	 * @param nameS
	 * @return
	 */
	private static String getImmediatePackage(String nameS) {
		if (nameS.indexOf(".") > 0) {
			String arr[] = nameS.split("\\.");
			return arr[arr.length - 1]; // return the last item
		} 
		return nameS;
	}
	
	/** If a string has ending braces '()' - this function will
	 * remove them
	 * @param arguement
	 * @return
	 */
	public static String removeEndingBraces(String arguement){
		if(arguement.endsWith("()")){
			return arguement.substring(0, arguement.lastIndexOf("()"));
		}
				
		return arguement;
	}

}
