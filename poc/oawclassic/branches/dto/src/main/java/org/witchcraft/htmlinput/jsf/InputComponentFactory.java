package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;


/** This is a factory used to create a renderer for html input based on the type of 
 * @author jsingh
 *
 */
public class InputComponentFactory {
	

	
	/** Get the right renderer for this attribute type
	 * @param attribute
	 * @return
	 */
	public static InputComponentRenderer getRenderer(Attribute attribute, RenderContext renderContext){
		if(attribute == null){
			System.out.println("Warning : null attribute");
		}
		
		String type = attribute.Type().NameS();
		
		if(type == null){
			System.out.println("Warning : type of attribute" + attribute.NameS() + " is null");
			return null;
		}
		
		InputComponentRenderer renderer =  getBasicRenderer(attribute, type);
		
		if(renderContext == RenderContext.Search)
			return new SearchRendererDecorator(renderer);
		
		return renderer; //return default
	}

	/** The renderer returned form this class will be decorated by the public getrenderer method 
	 * @param attribute
	 * @param type
	 * @return
	 */
	private static InputComponentRenderer getBasicRenderer(Attribute attribute, String type) {
		//System.out.println("Getting textRenderer for attribute " + attribute.NameS() + " of type "  + attribute.Type().getMetaClass().getSimpleName());
		if(type.equals("String")){
			return new InputTextRenderer();
		}else if (type.equals("java.util.Date") || type.equals("Date") ){
			return new CalendarRenderer();
		}else if (type.equals("java.lang.Boolean") || type.equalsIgnoreCase("Boolean") ){
			return new BooleanCheckBoxRenderer();
		}else if (type.equals("Image")){
			return new FileUploadRenderer();
		}else if (attribute.Type().getMetaClass().getSimpleName().equals("Enumeration")){
			return new SelectOneMenuRenderer();
		}else{//default to input text 
			return new InputTextRenderer();
		}
	}

}
