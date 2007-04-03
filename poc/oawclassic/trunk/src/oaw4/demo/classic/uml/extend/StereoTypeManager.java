package oaw4.demo.classic.uml.extend;

import org.openarchitectureware.meta.uml.classifier.Class;

/** This class mostly containly convenince methods to determine if 
 * a class is of a given stereotype
 * @author jsingh
 *
 */
public class StereoTypeManager {

	private static final String MAPPED_SUPERCLASS = "MappedSuperclass";
	private static final String ENTITY = "Entity";
	private static final String EMBEDDABLE = "Embeddable";

	public static boolean isEmbeddable(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(EMBEDDABLE);
	}
	
	public static boolean isMappedSuperClass(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(MAPPED_SUPERCLASS);
	}

	public static boolean isEntity(Class cls) {
		return cls.getMetaClass().getSimpleName().equals(ENTITY);
	}

}
