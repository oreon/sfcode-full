package org.witchcraft.model.xmlutils;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

/**
 * This class is a generic factory for creating objects from XML files or Strings
 * @author jsingh
 *
 */
public class ObjectFromXMLFactory {
	private static final Logger logger = Logger.getLogger(ObjectFromXMLFactory.class);

	
	
	/** This function will read the xmlfile and return an object of type S
	 * @param <S>
	 * @param xmlFileName -"assumes that xmlfilename is in resources directory -dont forget to add a "/"
	 *  before the filename
	 * @param setup
	 * @return
	 */
	public<S> S fromXMLFile(String xmlFileName, XStreamSetup setup){
		XStream xs = new XStream();
		
		setup.setupXStream(xs);
		InputStream stream = ObjectFromXMLFactory.class.getResourceAsStream(xmlFileName);
		if(stream == null){
			logger.error("The file "+ xmlFileName + " could not be found. - forgot to add a / ?");
			throw new RuntimeException("The file "+ xmlFileName + " could not be found.");
		}
		return (S)xs.fromXML(stream);
	}

}
