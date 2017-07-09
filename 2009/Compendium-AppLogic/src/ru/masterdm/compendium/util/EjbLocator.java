package ru.masterdm.compendium.util;

import java.util.HashMap;

import javax.ejb.Local;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ru.masterdm.compendium.exception.FactoryException;

/**
 * Ejb references locator class(Локатор сервисов).
 * 
 * @author IShafigullin
 */
public final class EjbLocator {

    private static final String WAS_LOCAL_EJB_PREFIX = "ejblocal:";

    private static EjbLocator locator;

    private HashMap<String, Object> referenceMap = new HashMap<String, Object>();
    private Context context;

    /**
     * Disable constructor for utility class.
     */
    private EjbLocator() {
    }

    /**
     * Singleton getInstance method.
     * @return this
     */
    public static EjbLocator getInstance() {
        if (locator == null) {
            locator = new EjbLocator();
        }
        return locator;
    }

    /**
     * Looks up for ejb reference.
     * @param clazz ejb interface (local or remote)
     * @param <T> reference type
     * @return T ejb reference
     * @throws FactoryException 
     */
    @SuppressWarnings("unchecked")
    public <T> T getReference(Class<T> clazz) throws FactoryException {
        String jndiPrefix = "";
        if (clazz.getAnnotation(Local.class) != null) {
            jndiPrefix = WAS_LOCAL_EJB_PREFIX;
        }
        T reference = (T) referenceMap.get(clazz.getName());
        if (reference == null) {
            try {
                reference = (T) getContext().lookup(jndiPrefix + clazz.getName());
                if (reference == null) {
                    throw new FactoryException(
                        "Cannot find remote reference for '" + clazz.getName() + "'"
                    );
                }
                referenceMap.put(clazz.getName(), reference);
            } catch (NamingException e) {
                throw new FactoryException(e.getMessage());
            }
        }
        return reference;
    }

    /**
     * Initializes and holds initial context.
     * @return {@link Context} instance
     * @throws FactoryException 
     */
    private Context getContext() throws FactoryException {
        try {
            if (context == null) {
                context = new InitialContext();
            }
        } catch (NamingException e) {
            throw new FactoryException(e.getMessage());
        }
        return context;
    }
}
