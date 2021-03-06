package ru.masterdm.compendium.command;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.masterdm.compendium.util.ApplProperties;
/**
 * @author IShafigullin
 */

public class CommandFactory {
	private static Map<String, Command> commandCache = null;
	private static final Logger LOGGER = Logger
	.getLogger(CommandFactory.class.getName());		

	/**
	 * CommandFactory constructor comment.
	 */
	public CommandFactory() {
		super();
	}

	/**
	 * Derive the name of the package and class based on the pattern
	 * Creation date: (3/12/2008 4:40:57 PM)
	 * @return String
	 */
	private static String computeCommandClassName(String commandType) {
		String packageName = ApplProperties.COMMAND_PACKAGE_PREFIX;
		String className = commandType + "Command";
		return packageName + className;
	}

	/**
	 * @return Command 
	 */
	public static Command getCommand(String name) {
		Command command = getCommandCache().get(name);
		if (command == null) {
			String classname = computeCommandClassName(name);
			Class<?> aClass = null;
			try {
				aClass = Class.forName(classname);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.SEVERE, classname + " not defined...");
			}
			try {
				command = (Command)aClass.newInstance();
				getCommandCache().put(name, command);

			} catch (InstantiationException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE,e.toString());
			}
		}
		return command;
	}


	private static Map<String, Command> getCommandCache() {
		if (commandCache == null)
			commandCache = new HashMap<String, Command>();
		return commandCache;
	}

	public static void resetCommands() {
		commandCache = null;
	}

}