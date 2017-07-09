/**
 * 
 */
package ru.masterdm.compendium.mapping.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.persistence.EntityManager;

import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.mapping.Mapper;
import ru.masterdm.compendium.mapping.ejb.DomainEJBMapper;
import ru.masterdm.compendium.util.EJBClientHelper;

/**
 * * This is the abstract superclass of all JPA Mappers.
 * @author IShafigullin
 *
 */

public abstract class DomainJPAMapper implements Mapper {
	private static EntityManager entityMgr = null;
	private static final Logger LOGGER = Logger.getLogger(DomainEJBMapper.class.getName());

	protected static EntityManager getEntityMgr() {
		if(entityMgr == null){
			try {
				entityMgr = (EntityManager) EJBClientHelper.getInitialContext().lookup("java:comp/env/CompendiumJPA");
			} catch (NamingException e) {
				LOGGER.log(Level.SEVERE, "Exception " + e + " in createEntityMgr()");
			}			
		}
		return entityMgr;
	}	

	/**
	 * Return an Enumeration that returns all of the JPA's for this type
	 * Creation date: (3/19/00 7:59:31 PM)
	 * @return Collection
	 */
	protected Collection<?> findAllJPAs() throws MappingException {
		throw new MappingException("FindAll not valid for this type");
	}	

	/**
	 * Return the JPAOBject matching this domain object
	 *
	 * Creation date: (3/20/00 11:57:12 AM)
	 * @return javax.jpa.JPAObject
	 * @param domainObject java.lang.Object
	 */
	protected abstract Object findJPAObjectMatching(VtbObject domainObject) throws MappingException;
	
	/**
	 * Return the result of mapping this JPAObject into a domain object.
	 *
	 * Creation date: (3/19/00 7:38:07 PM)
	 * @return ru.masterdm.compendium.domain.VtbObject
	 * @param input javax.jpa.JPAObject
	 */
	public abstract VtbObject map(Object jpaObject) throws MappingException;
	
	
	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.Mapper#remove(ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	public void remove(VtbObject domainObject) throws NoSuchObjectException, MappingException {
		Object jpa = findJPAObjectMatching(domainObject);
		try {
			getEntityMgr().remove(jpa);
		} catch (Exception e) {
			throw new MappingException(e, ("Wrapped Exception in DomainJPAMapper.remove():" + e));
		}
	}

	@Override
	public VtbObject findByPrimaryKey(VtbObject domainObjectWithKeyValues) throws NoSuchObjectException, MappingException {
		Object jpa = findJPAObjectMatching(domainObjectWithKeyValues);
		return map(jpa);
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.Mapper#findAll()
	 */
	@Override
	public ArrayList<Object> findAll() throws MappingException {
		Iterator<?> allJPAs = findAllJPAs().iterator();
		ArrayList<Object> list = new ArrayList<Object>();
		while (allJPAs.hasNext()) {
			Object next = allJPAs.next();
			Object mapped = map((Object) next);
			list.add(mapped);
		}
		return list;
	}
}
