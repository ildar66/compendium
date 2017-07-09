package ru.masterdm.compendium.mapping.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.mapping.Mapper;
import ru.masterdm.compendium.util.ApplProperties;
import ru.masterdm.compendium.util.EJBClientHelper;

/**
 * This is the abstract superclass of all DomainFactories.
 *
 * @author IShafigullin
 */
public abstract class JDBCMapper implements Mapper {

	static Properties contextProperties = new Properties();
	private static final Logger LOGGER = Logger
	.getLogger(JDBCMapper.class.getName());	

	{
		contextProperties.put("java.naming.provider.url", "iiop:///");
		contextProperties.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
	}

	/**
	 * DomainFactory constructor.
	 */
	public JDBCMapper() {
		super();
	}

	/**
	 * Create a new object into the persistent store; return the
	 * primary key object
	 */
	public java.lang.Object create(VtbObject domainObject) throws MappingException {
		Connection conn = null;
		try {
			// get a connection
			conn = getConnection();
			// single transaction.
			//conn.setAutoCommit(false);
			Object key = createImpl(conn, domainObject);
			//conn.commit();
			return key;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"Exception " + e + " caught in create()");
			throw new MappingException("Wrapped Exception " + e + " caught in create()");
		} finally {
			close(conn);
		}

	}

	/**
	 * Create a new object into the persistent store; return the
	 * primary key object
	 */
	protected java.lang.Object create(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		return createImpl(conn, domainObject);
	}

	/**
	 * Create a new object into the persistent store; return the
	 * primary key object
	 */
	protected abstract java.lang.Object createImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException;

	/**
	 * Return a List of all ILSDomainObject (use carefully in practice!)
	 * We use this extensively in our example, but in fact more "wise" enumerators
	 * That would directly query the datasource (e.g. through EJB finders)
	 *
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findAllObjects() throws MappingException {
		throw new MappingException("Create not valid for this type");
	}

	/**
	 * Retrieve a single object matching this object.
	 *
	 * @return ILSDomainObject
	 */
	public VtbObject findByPrimaryKey(VtbObject domainObjectWithKeyValues) throws NoSuchObjectException {
		Connection conn = null;
		VtbObject object = null;
		try {
			// get a connection
			conn = getConnection();
			// single transaction.
			//conn.setAutoCommit(false);
			object = findByPrimaryKeyImpl(conn, domainObjectWithKeyValues);
			//conn.commit();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"Exception " + e + " caught in findByPrimaryKey()");
			throw new NoSuchObjectException("Wrapped Exception " + e + " caught in findByPrimaryKey()");
		} finally {
			close(conn);
		}
		if (object == null)
			throw new NoSuchObjectException("No object found");
		return object;

	}

	/**
	 * Retrieve a single object matching this object.
	 *
	 * @return ILSDomainObject
	 */
	protected VtbObject findByPrimaryKey(Connection conn, VtbObject domainObjectWithKeyValues) throws SQLException, MappingException {
		return findByPrimaryKeyImpl(conn, domainObjectWithKeyValues);
	}

	/**
	 * Retrieve a single object matching this object.
	 *
	 * @return ILSDomainObject
	 */
	protected abstract VtbObject findByPrimaryKeyImpl(Connection conn, VtbObject domainObjectWithKeyValues) throws SQLException, MappingException;

	/**
	 * Remove the domain object from the persistent store.
	 *
	 * Creation date: (3/20/00 11:55:18 AM)
	 * @param domainObject ILSDomainObject
	 */
	public void remove(VtbObject domainObject) throws NoSuchObjectException {
		Connection conn = null;
		try {
			// get a connection
			conn = getConnection();
			// single transaction.
			//conn.setAutoCommit(false);
			removeImpl(conn, domainObject);
			//conn.commit();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"Exception " + e + " caught in remove()");
			throw new NoSuchObjectException("Wrapped Exception " + e + " caught in remove()");
		} finally {
			close(conn);
		}

	}

	/**
	 * Remove the domain object from the persistent store.
	 *
	 * Creation date: (3/20/00 11:55:18 AM)
	 * @param domainObject ILSDomainObject
	 */
	public void remove(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		removeImpl(conn, domainObject);
	}

	/**
	 * Remove the domain object from the persistent store.
	 *
	 */
	protected abstract void removeImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException;

	/**
	 * Update this object (e.g. change its state in the store)
	 *
	 */
	public void update(VtbObject domainObject) throws NoSuchObjectException {
		Connection conn = null;
		try {
			// get a connection
			conn = getConnection();
			// single transaction.
			//conn.setAutoCommit(false);
			updateImpl(conn, domainObject);
			//conn.commit();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"Exception " + e + " caught in update()");
			throw new NoSuchObjectException("Wrapped Exception " + e + " caught in update()");
		} finally {
			close(conn);
		}
	}

	/**
	 * Update this object (e.g. change its state in the store)
	 *
	 */
	protected void update(Connection conn, VtbObject anObject) throws SQLException, MappingException {
		updateImpl(conn, anObject);
	}

	/**
	 * Update this object (e.g. change its state in the store)
	 *
	 */
	protected abstract void updateImpl(Connection conn, VtbObject anObject) throws SQLException, MappingException;

	/**
	 * Access a Connection from the Datasource
	 * @return a managed Connection
	 */
	public static Connection getConnection() throws SQLException {
		// get a connection
		DataSource ds = getDataSource();
		return ds.getConnection();

	}

	public static void commitConnection() throws SQLException {
		// in XA transaction 
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/8/00 12:24:41 PM)
	 * @return javax.sql.DataSource
	 */
	public static DataSource getDataSource() {
		DataSource ds = null;
		try {
			InitialContext context = EJBClientHelper.getInitialContext();
			//javax.naming.InitialContext context = new InitialContext(contextProperties);
			ds = (DataSource) context.lookup(ApplProperties.getDatasourceJndiName());
		} catch (javax.naming.NamingException ne) {
			MappingException e = new MappingException("NamingException: cannot find DataSource in initialContext");
			LOGGER.info(e.getMessage());
		}
		return ds;
	}

	protected void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}
	
	/**
	 * 
	 */
	public void insert(VtbObject anObject) throws DuplicateKeyException, MappingException {
		Connection conn = null;
		try {
			conn = getConnection();
			createImpl(conn, anObject);
			return;
		} catch (SQLException se) {
			LOGGER.log(Level.SEVERE,"Exception " + se + " caught in insert()");			
			throw new DuplicateKeyException(se, ("Insert Failed " + anObject));
		} finally {
			close(conn);
		}
	}	

}