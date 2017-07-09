package ru.masterdm.compendium.model;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.masterdm.compendium.util.ApplProperties;

/**
 * Action ProcessorFactory.
 * Creation date: (2/27/2001 1:45:50 PM)
 * @author IShafigullin
 */

public class ActionProcessorFactory {
	private static Map<String, Object> processorCache = null;
	private static final Logger LOGGER = Logger
	.getLogger(ActionProcessorFactory.class.getName());

	/**
	 * ActionProcessorFactory constructor comment.
	 */
	public ActionProcessorFactory() {
		super();
	}

	/**
	 * Derive the name of the package and class based on the pattern
	 * Creation date: (3/2/2001 4:40:57 PM)
	 * @return String
	 */
	public static String computeActionProcessorClassName(String processorType) {
		String packageName = ApplProperties.PACKAGE_PREFIX + ApplProperties.getModelType().toLowerCase();
		String className = processorType + "ActionProcessorImpl";
		return packageName + "." + className;
	}

	/**
	 * @return adapter to application Model 
	 */
	public static Object getActionProcessor(String name) {
		Object processor = getProcessorCache().get(name);
		if (processor == null) {
			String classname = computeActionProcessorClassName(name);
			Class<?> aClass = null;
			try {
				aClass = Class.forName(classname);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, classname + " not defined...");
			}
			try {
				processor = aClass.newInstance();
				getProcessorCache().put(name, processor);

			} catch (InstantiationException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			}
		}
		return processor;
	}


	private static Map<String, Object> getProcessorCache() {
		if (processorCache == null)
			processorCache = new HashMap<String, Object>();
		return processorCache;
	}

	public static void resetActionProcessors() {
		processorCache = null;
	}

}