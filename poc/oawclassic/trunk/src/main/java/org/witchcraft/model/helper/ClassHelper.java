package org.witchcraft.model.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import oaw4.demo.classic.uml.extend.GenericUtils;
import oaw4.demo.classic.uml.extend.StereoTypeManager;
import oaw4.demo.classic.uml.meta.Entity;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;

/**
 * Contains methods to help with oaw class e.g. get all superclasses , get all
 * attributes etc.
 * 
 * @author jsingh
 * 
 */
public class ClassHelper {

	/**
	 * @returns a collection of the all superclasses of the given class
	 */
	public static Set<Class> getSuperClasses(Class cls) {

		Set<Class> superClasses = new HashSet<Class>();

		Class tempClass = cls;

		while (tempClass.hasSuperClass()) {
			superClasses.add(tempClass.SuperClass());
			tempClass = tempClass.SuperClass();
		}

		return superClasses;
	}

	/**
	 * This function all attributes for a given class - including those
	 * inherited from superclasses as well as the associations
	 * 
	 * @param cls
	 * @return
	 */
	public static List getAllAttributes(Class cls) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.addAll(cls.Attribute());
		// superclasses
		for (Class superCls : getSuperClasses(cls)) {
			attributes.addAll(superCls.Attribute());
		}
		// associations
		for (AssociationEnd ae : getAllAssociations(cls)) {
			Attribute attribute = new Attribute();
			attribute.setName(ae.NameS());
			attributes.add(attribute);
		}

		return attributes;
	}

	/**
	 * Return the unique attributes e.g (Email) - searches in the entity,
	 * superclasses and all embeddable components
	 * 
	 * @param cls
	 * @return
	 */
	public static List getUnique(Entity cls) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.addAll(cls.Attribute());
		// superclasses
		for (Class superCls : getSuperClasses(cls)) {
			attributes.addAll(superCls.Attribute());
		}
		// associations
		for (Iterator iter = cls.AssociationEnd().iterator(); iter.hasNext();) {
			AssociationEnd ae = (AssociationEnd) iter.next();
			// if embeddable we need all the attributes of the contained class
			if (StereoTypeManager.isEmbeddable(ae.Opposite().Class()))
				attributes.addAll(ae.Opposite().Class().Attribute());
		}
		return attributes;
	}

	/**
	 * Get all navigable associations
	 * 
	 * @param cls
	 * @return
	 */
	public static List<AssociationEnd> getAllAssociations(Class cls) {

		List<AssociationEnd> associationEnds = new ArrayList<AssociationEnd>();

		for (Iterator iter = cls.AssociationEnd().iterator(); iter.hasNext();) {
			AssociationEnd ae = (AssociationEnd) iter.next();
			if (ae.Opposite().isNavigable())
				associationEnds.add(ae);
		}

		return associationEnds;
	}

	/**
	 * REturns the name of the getter method for the given attribute
	 * 
	 * @param string
	 * @return
	 */
	public static String getterFor(String attributeName) {
		// TODO Auto-generated method stub
		return "get" + GenericUtils.toFirstUpper(attributeName);
	}

	/**
	 * Retunrs the name of the setter method for the given attribute
	 * 
	 * @param attributeName
	 * @return
	 */
	public static String setterFor(String attributeName) {
		// TODO Auto-generated method stub
		return "set" + GenericUtils.toFirstUpper(attributeName);
	}

	public static String getDefaultInitValueForType(String type) {
		if(type == null) return "0vvv";
		String[] primitives = { "Integer", "int", "double", "Double" };
		if (ArrayUtils.contains(primitives, type))
			return " 0 ";
		else
			return " new " + type + "()";
	}
}
