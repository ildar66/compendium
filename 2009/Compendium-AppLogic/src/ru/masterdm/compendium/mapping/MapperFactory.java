package ru.masterdm.compendium.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.masterdm.compendium.system.ClassUtility;
import ru.masterdm.compendium.util.ApplProperties;


/**
 * Master mapper factory. 
 * @author IShafigullin
 */
public abstract class MapperFactory {
	private static MapperFactory systemMapperFactory = null;
	private static MapperFactory reserveMapperFactory = null;
	protected Map<String, Mapper> mapperCache = new HashMap<String, Mapper>();
	private static final Logger LOGGER = Logger.getLogger(MapperFactory.class.getName());

	/**
	 * MapperFactory constructor comment.
	 */
	public MapperFactory() {
		super();
	}

	/**
	 *  Select appropriate classname depending on installed
	 *  SystemMapperFactory.
	 */
	public String computeMapperClassName(Class<?> target) {
		String packageName =
			ClassUtility.computePackageName(target, "domain", "mapping");
		// get specific package qualifier from installed Factory
		packageName = packageName + "." + getBackendQualifier();
		String className = ClassUtility.unqualifiedClassName(target) + "Mapper";
		return packageName + "." + className;
	}

	protected abstract String getBackendQualifier();


	/**
	 * Create and return a Mapper instance for the class
	 * This is accomplished by suffixing a class with "Mapper"
	 */
	public Mapper getMapper(Class<?> target) {
		String aMapperName = computeMapperClassName(target);

		// check registry cache for broker...
		Mapper aMapper = (Mapper) getMapperCache().get(aMapperName);
		if (aMapper != null)
			return aMapper;

		// create the mapper and save
		Class<?> aClass = null;
		try {
			aClass = Class.forName(aMapperName);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE,"Mapper, " + aMapperName + ", not defined...");
		}
		try {
			aMapper = (Mapper) aClass.newInstance();
			getMapperCache().put(aMapperName, aMapper);
		} catch (InstantiationException e) {
			LOGGER.log(Level.SEVERE,e.toString());
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.SEVERE,e.toString());
		}
		return aMapper;
	}

	public Map<String, Mapper> getMapperCache() {
		return mapperCache;
	}

	private static Map<String, String> getMapperFactoryNameMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ApplProperties.EJB_MAPPER, ApplProperties.EJB_MAPPER_FACTORY);
		map.put(ApplProperties.JDBC_MAPPER, ApplProperties.JDBC_MAPPER_FACTORY);
		map.put(ApplProperties.MEMORY_MAPPER, ApplProperties.MEMORY_MAPPER_FACTORY);
		map.put(ApplProperties.JPA_MAPPER, ApplProperties.JPA_MAPPER_FACTORY);
		return map;
	}

	public static MapperFactory getSystemMapperFactory() {
		if (systemMapperFactory == null) {
			String className =
				(String) getMapperFactoryNameMap().get(ApplProperties.getCurrentMapperName());
			Class<?> aClass = null;
			try {
				aClass = Class.forName(className);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE,"Mapper Factory, " + className + ", not defined...");
			}
			try {
				setSystemMapperFactory((MapperFactory) aClass.newInstance());
			} catch (InstantiationException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			}

		}
		return systemMapperFactory;

	}

	private static void setSystemMapperFactory(MapperFactory factory) {
		systemMapperFactory = factory;
	}

/*	public static void setSystemMapperFactory(String id) {
		setCurrentMapperName(id);
		systemMapperFactory = null;
		getSystemMapperFactory();
	}
	*/
	
	/**
	 * @return
	 */
	public static MapperFactory getReserveMapperFactory() {
		if (reserveMapperFactory == null) {
			String className =
				(String) getMapperFactoryNameMap().get(ApplProperties.getReserveMapperName());
			Class<?> aClass = null;
			try {
				aClass = Class.forName(className);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE,"Reserve Mapper Factory, " + className + ", not defined...");
			}
			try {
				setReserveMapperFactory((MapperFactory) aClass.newInstance());
			} catch (InstantiationException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			}

		}
		return reserveMapperFactory;
	}

	/**
	 * @param factory
	 */
	private static void setReserveMapperFactory(MapperFactory factory) {
		reserveMapperFactory = factory;
	}	
}