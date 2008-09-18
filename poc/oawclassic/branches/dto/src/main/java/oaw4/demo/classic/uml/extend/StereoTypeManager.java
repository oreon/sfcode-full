package oaw4.demo.classic.uml.extend;

import org.openarchitectureware.core.frontends.xmi.mapping.Mapping.ModelElement;
import org.openarchitectureware.core.meta.core.Element;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;

import org.openarchitectureware.meta.uml.classifier.Parameter;

/** This class mostly containly convenince methods to determine if 
 * a class is of a given stereotype
 * @author jsingh
 *
 */
public class StereoTypeManager {

	private static final String MAPPED_SUPERCLASS = "MappedSuperclass";
	private static final String ENTITY = "Entity";
	private static final String EMBEDDABLE = "Embeddable";
	private static final String COLUMN = "Column";
	private static final String CONSTRAINED_PARAMETER = "ConstrainedParameter";

	public static boolean isEmbeddable(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(EMBEDDABLE);
	}
	
	public static boolean isColumn(Element me) {
		return me.getMetaClass().getSimpleName().equals(COLUMN);
	}
	
	public static boolean isMappedSuperClass(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(MAPPED_SUPERCLASS);
	}

	public static boolean isEntity(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(ENTITY);
	}
	
	public static boolean isConstrainedParameter(Parameter cls) {
		return cls.getMetaClass().getSimpleName().equals(CONSTRAINED_PARAMETER);
	}

}
