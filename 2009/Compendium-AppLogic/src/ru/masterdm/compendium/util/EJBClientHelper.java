package ru.masterdm.compendium.util;

import java.util.Hashtable;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
/**
 * @author IShafigullin
 * 
 * Utilty class providing caching services for JNDI InitialContext and 
 * EJB Home objects
 */
public class EJBClientHelper {

	private static InitialContext context = null;
	private static Hashtable<String, EJBHome> homesCache = null;
	private static Hashtable<String, EJBLocalHome> homesLocalCache = null;
	private static final String REFERENCE_NAME_PREFIX = "java:comp/env/ejb/";

	/**
	 * Access to a cached reference to local JNDI base Naming context
	 * @returns valid InitialContext.
	 */
	public static InitialContext getInitialContext() throws NamingException {
		if (context == null) {
			context = new InitialContext();
		}

		return context;
	}

	protected static Hashtable<String, EJBHome> getCache() {
		if (homesCache == null)
			homesCache = new Hashtable<String, EJBHome>();
		return homesCache;
	}

	protected static Hashtable<String, EJBLocalHome> getLocalCache() {
		if (homesLocalCache == null)
			homesLocalCache = new Hashtable<String, EJBLocalHome>();
		return homesLocalCache;
	}

	/**
	 * Used to reset the Home Cache.  This permits forcing reloading homes from JNDI.
	 */
	public static void flushCache() {
		homesCache = null;
	}

	/**
	 * Locate EJB home (from Home Cache).  Uses local EJB References to 
	 * locate EJB Home.  The returned EJBHome object has been narrowed.
	 * @param refName the local EJB Reference.  This String will be prepended with 
	 *       "java:comp/env/ejb/" to form the full EJB Reference.
	 * @param homeClass the EJB Home class, used to narrow the home object returned 
	 *        by JNDI
	 * @returns The EJBHome
	 */
	public static EJBHome getHome(String refName, Class<?> homeClass) throws NamingException {
		Hashtable<String, EJBHome> cache = getCache();
		EJBHome home = null;
		home = (EJBHome) cache.get(refName);
		if (home == null) {
			String ejbReference = REFERENCE_NAME_PREFIX + refName;
			home = (EJBHome) PortableRemoteObject.narrow(getInitialContext().lookup(ejbReference), homeClass);
			cache.put(refName, home);
		}
		return home;
	}

	/**
	 * Locate EJB home (from Home Cache).  Uses local EJB References to 
	 * locate EJB Home.  The returned EJBHome object has been narrowed.
	 * @param refName the local EJB Reference.  This String will be prepended with 
	 *       "java:comp/env/ejb/" to form the full EJB Reference.
	 * @param homeClass the EJB Home class, used to narrow the home object returned 
	 *        by JNDI
	 * @returns The EJBHome
	 */
	public static EJBLocalHome getLocalHome(String refName) throws NamingException {
		Hashtable<String, EJBLocalHome> cache = getLocalCache();
		EJBLocalHome home = null;
		home = (EJBLocalHome) cache.get(refName);
		if (home == null) {
			String ejbReference = REFERENCE_NAME_PREFIX + refName;
			home = (EJBLocalHome) getInitialContext().lookup(ejbReference);
			cache.put(refName, home);
		}
		return home;
	}

}