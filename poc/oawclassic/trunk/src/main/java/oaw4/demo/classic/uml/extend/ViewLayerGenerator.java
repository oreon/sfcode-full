package oaw4.demo.classic.uml.extend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oaw4.demo.classic.uml.meta.Entity;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.openarchitectureware.core.meta.core.Element;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.core.meta.visitor.ModelElementVisitor;
import org.openarchitectureware.core.meta.visitor.TypeCollectingVisitor;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.state.Branch;
import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.StateMachine;
import org.openarchitectureware.meta.uml.state.Transition;
import org.witchcraft.htmlinput.jsf.InputComponentFactory;
import org.witchcraft.htmlinput.jsf.InputComponentRenderer;
import org.witchcraft.htmlinput.jsf.RenderContext;

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

	private static RenderContext renderContext = RenderContext.Edit;

	private static Map<String, ElementSet> stateMachineAndStates = new HashMap<String, ElementSet>();
	
	private static Set viewStates = new HashSet();
	
	private static Set<String> guardNames = new HashSet<String>();
	
	private static ElementSet transitionSet = new ElementSet();

	

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
		if (stateMachineAndStates.get(stateMachine.NameS()) == null) {
			System.out.println("adding states for stateMachine " + stateMachine.Name());
			ModelElementVisitor visitor = new TypeCollectingVisitor(
					stateMachine, State.class);
			
			stateMachine.visit(visitor);
			ElementSet states = ((TypeCollectingVisitor) visitor)
					.getCollectedElements();
			states = getStatesForThisStateMachine(stateMachine, states);
			dumpSet(states);
			viewStates.addAll(states);
			stateMachineAndStates.put(stateMachine.NameS(), states);
		}
		return stateMachineAndStates.get(stateMachine.NameS());
	}
	
	public static ElementSet getAllStates(){
		ElementSet states = new ElementSet();
		states.addAll(viewStates);
		return states;
	}
	
	/**
	 * @return
	 */
	public static ElementSet getUniqueTransitions(){
		
		StateMachine tempStateMachine = null;
		
		for (Object stateObj : viewStates) {
			State state = (State)stateObj;
			tempStateMachine = state.StateMachine();
			ElementSet transitions = state.OutTransition();
			getTransitionsForState(transitionSet, transitions);
			
			ElementSet branches = getBranches(tempStateMachine);
			//System.out.println( branches.size() + " Branches ->" + tempStateMachine.NameS());
			getTransitonsForBranches(branches); 
		}
		
		
		
		
		System.out.println("====== TRANSITIONS ========");
		dumpSet(transitionSet);
		return transitionSet;
	}

	private static void getTransitonsForBranches(ElementSet branches) {
		for (Object stateObj : branches) {
			Branch branch = (Branch)stateObj;
			ElementSet transitions = branch.OutTransition();
			//System.out.println("====== Branch TRANSITIONS ========");
			//dumpSet(transitions);
			getTransitionsForState(transitionSet, transitions);
		}
	}

	private static void getTransitionsForState(ElementSet transitionSet,
			ElementSet transitions) {
		for (Object trobject : transitions) {
			Transition transition = (Transition)trobject;
			
			if(!StringUtils.isEmpty(transition.Guard())){
				
				if(!guardNames.contains(transition.Guard())){
					transitionSet.add(transition);
					guardNames.add(transition.Guard());
				}
			}
		}
	}

	/** filter the states to return only this machines states
	 * @param stateMachine
	 * @param states
	 * @return
	 */
	private static ElementSet getStatesForThisStateMachine(
			StateMachine stateMachine, ElementSet states) {
		ElementSet statesForThisStateMachine = new ElementSet();
		for (Object object : states) {
			State state = (State)object;
			if(StringUtils.equals(state.StateMachine().NameS(),  stateMachine.NameS() ) ) 
				statesForThisStateMachine.add(state);
		}
		return statesForThisStateMachine;
	}

	public static ElementSet getBranches(StateMachine stateMachine) {
		ModelElementVisitor visitor = new TypeCollectingVisitor(stateMachine,
				Branch.class);
		ElementSet branches = ((TypeCollectingVisitor) visitor)
				.getCollectedElements();
		return branches;
	}

	/**
	 * For all states that have even one guard - we need to generate backing
	 * beans - this function gets a list of such states
	 * 
	 * @param stateMachine
	 * @return
	 */
	public static ElementSet getStatesWithGuards(StateMachine stateMachine) {
		ElementSet statesWithGuards = new ElementSet();
		ElementSet states = getStates(stateMachine);

		for (Object object : states) {
			State state = (State) object;
			for (Object trans : state.OutTransition()) {
				Transition transition = (Transition) trans;
				if (transition == null)
					continue;
				if (transition.hasAction())
					System.out.println(transition.NameS() + " - action - "
							+ transition.Action().NameS());

				String guard = transition.Guard();
				if (guard != null
						&& !StringUtils.EMPTY.equals(transition.Guard())) {
					if (guard.contains(".")) {// this guard is using a crud
												// backing bean
						System.out.println("Gurard uses backing bean");
					} else {
						System.out.println(" adding transition with guard "
								+ transition.Guard());
						statesWithGuards.add(state);
						break;
					}
				}

			}
		}

		return statesWithGuards;
	}

	private static void dumpSet(ElementSet set) {
		for (Object object : set) {
			Element element = (Element) object;
			
			if(element instanceof Transition){
				System.out.println( ((Transition)element).Guard() + "--");
			}else
			System.out.println(element.Name() + "--");
		}
		System.out.println("-------------------------------------");
	}

	public static InputComponentRenderer getInputComponent(Attribute attribute,
			RenderContext renderContext) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext());
	}

	public static String getElementContent(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext())
				.getContent(attribute);
	}

	public static String getElementValidatorContent(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext())
				.getValidatorContent(attribute);
	}

	public static String getElementType(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext())
				.getType(attribute);
	}

	public static String getElementAttributes(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext())
				.getAttributes(attribute);
	}

	public static boolean getElementRequired(Attribute attribute) {
		return InputComponentFactory.getRenderer(attribute, getRenderContext())
				.isRequired(attribute);
	}

	/**
	 * Get the left nav entities
	 * 
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
						+ " \"  expanded=\"true\" >");
			}

			data.append(" <rich:panelMenuItem><h:commandLink action=\"list"
					+ entity.NameS() + "\" value=\"#{msg." + entity.NameS()
					+ "}s\">\n");
			data.append("\t<f:param name=\"nextPage\" value=\""
					+ getImmediatePackage(entity.Namespace().NameS()) + "-"
					+ entity.NameS()
					+ "\" />\n</h:commandLink>\n</rich:panelMenuItem>\n");

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
	public static String getImmediatePackage(String nameS) {
		if (nameS.indexOf(".") > 0) {
			String arr[] = nameS.split("\\.");
			return arr[arr.length - 1]; // return the last item
		}
		return nameS;
	}

	/**
	 * If a string has ending braces '()' - this function will remove them
	 * 
	 * @param arguement
	 * @return
	 */
	public static String removeEndingBraces(String arguement) {
		return removeTrailing(arguement, "()");
	}

	public static String removeTrailingS(String arguement) {

		return removeTrailing(arguement, "s");
	}

	public static String removeTrailing(String arguement,
			String trailingPartToRemove) {
		if (arguement == null)
			return null;

		if (arguement.endsWith(trailingPartToRemove)) {
			return arguement.substring(0, arguement
					.lastIndexOf(trailingPartToRemove));
		}

		return arguement;
	}

	public static RenderContext getRenderContext() {
		return renderContext;
	}

	public static void setRenderContext(RenderContext renderContext) {
		ViewLayerGenerator.renderContext = renderContext;
	}

	public static void setSearchAsCurrentRenderContext() {
		ViewLayerGenerator.renderContext = RenderContext.Search;
	}

	public static void setEditAsCurrentRenderContext() {
		ViewLayerGenerator.renderContext = RenderContext.Edit;
	}
}
