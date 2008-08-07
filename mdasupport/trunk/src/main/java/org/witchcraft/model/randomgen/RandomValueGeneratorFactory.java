package org.witchcraft.model.randomgen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.witchcraft.model.randomgen.factories.RandomBooleanFactory;
import org.witchcraft.model.randomgen.factories.RandomDateFactory;
import org.witchcraft.model.randomgen.factories.RandomDoubleFactory;
import org.witchcraft.model.randomgen.factories.RandomEnumerationFactory;
import org.witchcraft.model.randomgen.factories.RandomIntFactory;
import org.witchcraft.model.randomgen.factories.RandomStringFactory;



/**
 * @author jsingh
 *
 */
public class RandomValueGeneratorFactory {
	
	private static Map mapFactoryToType = new HashMap<String, RandomValueCreator>();
	private static final Logger logger = Logger.getLogger(RandomValueGeneratorFactory.class);
	
	static {
		mapFactoryToType.put("String", new RandomStringFactory());
		mapFactoryToType.put("Date", new RandomDateFactory());
		mapFactoryToType.put("java.util.Date", new RandomDateFactory());
		mapFactoryToType.put("Boolean", new RandomBooleanFactory());
		mapFactoryToType.put("boolean", new RandomBooleanFactory());
		mapFactoryToType.put("Double", new RandomDoubleFactory());
		mapFactoryToType.put("double", new RandomDoubleFactory());
		mapFactoryToType.put("Integer", new RandomIntFactory());
		mapFactoryToType.put("int", new RandomIntFactory());
		mapFactoryToType.put("Enum", new RandomEnumerationFactory());
	}
	
	
	
	/** Creates an instance of the given clazztype
	 * @param clazz
	 * @return
	 */
	public static Object createInstance(String clazz) {
		RandomValueCreator creator = getCreatorByClass(clazz);
		return creator.createOne();
	}
	
	/** Creates an instance of the given clazztype
	 * @param clazz
	 * @return
	 */
	public static Object createUnique(String clazz) {
		RandomValueCreator creator = getCreatorByClass(clazz);
		return creator.createUnique();
	}
	
	private static RandomValueCreator getCreatorByClass(String clazz) {
		RandomValueCreator creator = (RandomValueCreator) mapFactoryToType.get(clazz);
		if(creator == null){
			logger.warn("Type "  + clazz + " not registerd for random generation ");
			return null;
		}
		return creator;
	}
	
	/** Creates an instance of the given class - this method is to be used by enums or
	 * other classes which need to return randomly one of the given values
	 * @param clazz
	 * @return
	 */
	public static Object createInstance(Object[] arrObjects) {
		RandomValueCreator creator = getCreatorByClass("Enum");
		return creator.createOne(arrObjects );
	}
	
	/** Creates an instance of the given class - this method is to be used by enums or
	 * other classes which need to return randomly one of the given values
	 * @param clazz
	 * @return
	 */
	public static Object createInstance(Object[] arrObjects, String clazz) {
		RandomValueCreator creator = getCreatorByClass(clazz);
		return creator.createOne(arrObjects );
	}

}
