package ru.masterdm.compendium.mapping;

/**
 * Jdbc MapperFactory
 * @author: IShafigullin
 */
public class JdbcMapperFactory extends MapperFactory {
/**
 * JdbcMapperFactory constructor comment.
 */
public JdbcMapperFactory() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/29/2001 11:32:41 AM)
 * @return java.lang.String
 */
protected String getBackendQualifier() {
	return "jdbc";
}
}
