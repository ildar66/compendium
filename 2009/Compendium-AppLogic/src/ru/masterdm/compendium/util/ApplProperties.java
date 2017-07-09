package ru.masterdm.compendium.util;

import java.util.PropertyResourceBundle;

/**
 * Настройки системы.
 * @author IShafigullin
 *
 */
public class ApplProperties {

	public static final String EJB_MODEL = "EJB";
	public static final String LOCAL_MODEL = "WEB";
	
	public static final String PACKAGE_PREFIX =	"ru.masterdm.compendium.model.";
	public static final String COMMAND_PACKAGE_PREFIX = "ru.masterdm.compendium.command.";
	public static final String MAPPING_PACKAGE_PREFIX = "ru.masterdm.compendium.mapping.";
	
	public static final String EJB_MAPPER = "EJB";
	public static final String JDBC_MAPPER = "JDBC";
	public static final String MEMORY_MAPPER = "MEMORY";
	public static final String JPA_MAPPER = "JPA";
	
	public static final String EJB_MAPPER_FACTORY = MAPPING_PACKAGE_PREFIX + "EjbMapperFactory";
	public static final String JDBC_MAPPER_FACTORY = MAPPING_PACKAGE_PREFIX + "JdbcMapperFactory";
	public static final String MEMORY_MAPPER_FACTORY = MAPPING_PACKAGE_PREFIX + "MemoryMapperFactory";
	public static final String JPA_MAPPER_FACTORY = MAPPING_PACKAGE_PREFIX + "JpaMapperFactory";	

	// Configuration file
    private static final String CONFIG_PROP_FILE = "vtbConfig";
    private static PropertyResourceBundle properties = (PropertyResourceBundle) PropertyResourceBundle.getBundle(CONFIG_PROP_FILE);
    
    public static String currentMapperName = properties.getString("vtb.persistence.name");
    public static String reserveMapperName = properties.getString("vtb.persistence.reserve.name");
    
    public static String modelType = properties.getString("vtb.model.impl.name");
    
    public static String datasourceJndiName = properties.getString("vtb.datasource.name");
    public static String datasourceSchema = properties.getString("vtb.datasource.schema");

    

	/**
	 * Gets the currentMapperName
	 * @return Returns a String
	 */
	public static String getCurrentMapperName() {
		return currentMapperName;
	}
	
	/**
	 * Gets the reserveMapperName
	 * @return Returns a String
	 */
	public static String getReserveMapperName() {
		return reserveMapperName;
	}	
	
	/**
	 * Gets the modelType
	 * @return Returns a String
	 */
	public static String getModelType() {
		return modelType;
	}

	/**
	 * Gets the datasourceJndiName
	 * @return Returns a String
	 */
	public static String getDatasourceJndiName() {
		return datasourceJndiName;
	}

	/**
	 * Gets the datasourceSchema
	 * @return Returns a String
	 */
	public static String getDatasourceSchema() {
		return datasourceSchema;
	}

}

