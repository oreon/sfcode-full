package org.witchcraft.htmlinput.jsf;

import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Enumeration;

/** File upload renderer for types - image,file 
 * @author jsingh
 *
 */
public class FileUploadRenderer extends AbstractInputComponentRenderer{

	
	public String getType(Attribute attribute) {
		
		// TODO Auto-generated method stub
		return "t:inputFileUpload";
	}
	
	@Override
	public String getAttributes(Attribute attribute) {
		// TODO Auto-generated method stub
		return super.getAttributes(attribute) +  " accept=\"image/*\"";
	}

	
}



