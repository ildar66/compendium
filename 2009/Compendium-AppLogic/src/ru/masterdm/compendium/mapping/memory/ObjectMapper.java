package ru.masterdm.compendium.mapping.memory;

import java.io.Serializable;
import java.util.ArrayList;

import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.mapping.Mapper;

/**
 * This class represents a simple, "in-memory" mapper for VtbObjects.
 * It stores the objects in a ArrayList.
 *
 * @author IShafigullin
 */
public abstract class ObjectMapper implements Mapper, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//protected ArrayList cache = null;
	/**
	 * ObjectMapper constructor.
	 */
	public ObjectMapper() {
		super();
	}
	
	protected abstract ArrayList<VtbObject> getCache();
	
	/**
	 * Remove all entries from the cache.
	 * Required for the JUnit Test Cases.
	 */
	public abstract void clearCache();
	
	/**
	 * Remove this TsObject from the cache.
	 */
	public void remove(VtbObject anObject) {
		ArrayList<?> cache = getCache();
		synchronized(this.getClass()) {
			cache.remove(anObject);
		}
	}
	/**
	 * Return a ArrayList of TsObjects for a Mapper.
	 * This should be overriden for each Mapper that requires
	 * an initial population of objects
	 *
	 * Creation date: (2/3/00 9:15:42 AM)
	 * @return java.util.ArrayList
	 */
	abstract ArrayList<?> initialLoad();

	/**
	 * Insert TsObject into cache, and toggle persistent flag.
	 */
	public void insert(VtbObject anObject) {
		synchronized(this.getClass()) {
			ArrayList<VtbObject> cache = getCache();
			cache.add(anObject);
		}
	}

	/**
	 * Retrieve all objects in this mapper.
	 * if first access, load cache first.
	 */
	@SuppressWarnings("unchecked")
	public java.util.ArrayList findAll() {
			ArrayList cache = getCache();
		return (ArrayList) cache.clone();
	}

	/**
	 * Default retrieveSingleObjectMatching() returns null
	 */
	public abstract VtbObject findByPrimaryKey(VtbObject anObject) throws NoSuchObjectException;
	
	/**
	 * The default update() method does a remove and an insert
	 */
	public void update(VtbObject anObject) {
		remove(anObject);
		insert(anObject);
	}
}