/**
 * 
 */
package ru.masterdm.compendium.mapping.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ru.masterdm.compendium.domain.ProcessType;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.util.ApplProperties;

/**
 * Mapper "тип процесса" implementation.
 * @author IShafigullin
 *
 */
public class ProcessTypeMapper extends JDBCMapper implements ru.masterdm.compendium.mapping.ProcessTypeMapper {
	
	protected static final String findByNameSqlString = "SELECT ID_TYPE_PROCESS id, DESCRIPTION_PROCESS description FROM "
		+ ApplProperties.getDatasourceSchema() + ".TYPE_PROCESS WHERE LOWER(DESCRIPTION_PROCESS) like LOWER(?)";
	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jdbc.JDBCMapper#createImpl(java.sql.Connection, ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	protected Object createImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jdbc.JDBCMapper#findByPrimaryKeyImpl(java.sql.Connection, ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	protected VtbObject findByPrimaryKeyImpl(Connection conn, VtbObject domainObjectWithKeyValues) throws SQLException,
			MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jdbc.JDBCMapper#removeImpl(java.sql.Connection, ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	protected void removeImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jdbc.JDBCMapper#updateImpl(java.sql.Connection, ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	protected void updateImpl(Connection conn, VtbObject anObject) throws SQLException, MappingException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.ProcessTypeMapper#findByName(java.lang.String, java.lang.String)
	 */
	public ArrayList<ProcessType> findByName(String name, String orderBy) throws MappingException {
		ArrayList<ProcessType> list = new ArrayList<ProcessType>();
		ProcessType vo = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer(findByNameSqlString);
		try {
			conn = getConnection();
			PreparedStatement ps = null;
			if (orderBy != null && !orderBy.equals("")) {
				sb.append(" order by " + orderBy);
			}
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, name);
			//System.out.println("sql = " + sb.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vo = activate(rs);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findByName code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected ProcessType activate(ResultSet rs) throws SQLException {
		ProcessType vo = new ProcessType(((java.math.BigDecimal) rs.getObject(1)).intValue(), rs.getString(2).trim());
		// ownershipFormType.setIsActive(("Y".equals(rs.getString(5)) ?
		// Boolean.TRUE : Boolean.FALSE));
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.Mapper#findAll()
	 */
	public ArrayList<?> findAll() throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

}
