package ru.masterdm.compendium.mapping;

/**
 * Jpa MapperFactory
 * @author: IShafigullin
 */
public class JpaMapperFactory extends MapperFactory {
/**
 * JpaMapperFactory constructor comment.
 */
public JpaMapperFactory() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/29/2001 11:32:19 AM)
 * @return java.lang.String
 */
protected String getBackendQualifier() {
	return "jpa";
}
}
