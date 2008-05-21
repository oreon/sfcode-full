package com.oreon.kgauge.web.jsf;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

//import com.sun.facelets.tag.AbstractTagLibrary;

public final class WCTagLib/* extends AbstractTagLibrary */ {

	public static final String NAMESPACE = "http://witchcraft.sourceforge.net/jsf";

	/** Current instance of library. */
	public static final WCTagLib INSTANCE = new WCTagLib();

	public WCTagLib() {
		//super(NAMESPACE);
		/*	
		try {
			Method[] methods = JsfFunctions.class.getMethods();

			for (int i = 0; i < methods.length; i++) {
				if (Modifier.isStatic(methods[i].getModifiers())) {
					this.addFunction(methods[i].getName(), methods[i]);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/

	}

}
