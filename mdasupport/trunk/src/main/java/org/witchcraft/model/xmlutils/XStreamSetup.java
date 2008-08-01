package org.witchcraft.model.xmlutils;

import com.thoughtworks.xstream.XStream;

/** This interface should be implemented by clients of ObjectFromXMLFactory
 * @author jsingh
 *
 */
public interface XStreamSetup {
	
	public void setupXStream(XStream xstream);

}
