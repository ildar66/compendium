package ru.masterdm.compendium.mapping;

/**
 * Memory MapperFactory
 * Creation date: (1/29/2001 11:32:19 AM)
 * @author IShafigullin
 */
public class MemoryMapperFactory extends MapperFactory {
/**
 * MemoryMapperFactory constructor comment.
 */
public MemoryMapperFactory() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/29/2001 11:32:19 AM)
 * @return java.lang.String
 */
protected String getBackendQualifier() {
	return "memory";
}
}
