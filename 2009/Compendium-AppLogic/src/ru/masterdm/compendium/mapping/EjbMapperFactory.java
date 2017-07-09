package ru.masterdm.compendium.mapping;

/**
 * Ejb MapperFactory
 * @author: IShafigullin
 */
public class EjbMapperFactory extends MapperFactory {
/**
 * EjbMapperFactory constructor comment.
 */
public EjbMapperFactory() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/29/2001 11:32:19 AM)
 * @return java.lang.String
 */
protected String getBackendQualifier() {
	return "ejb";
}
}