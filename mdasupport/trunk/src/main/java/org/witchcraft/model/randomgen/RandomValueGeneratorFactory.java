package org.witchcraft.model.randomgen;

import java.util.HashMap;
import java.util.Map;

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
	/**
	 * The renderer returned form this class will be decorated by the public
	 * getrenderer method
	 * 
	 * @param attribute
	 * @param type
	 * @return
	 */
	public static Object createInstance(String clazz) {
		
		RandomValueCreator creator = (RandomValueCreator) mapFactoryToType.get(clazz);
		if(creator == null){
			//log.warn("Type "  + type + " not registerd for random generation ");
			return null;
		}
		return creator.createOne();
		
	}

}
