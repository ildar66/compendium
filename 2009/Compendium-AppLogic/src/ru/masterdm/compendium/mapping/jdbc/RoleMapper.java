/*
 * Created on 24.07.2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.compendium.mapping.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ru.masterdm.compendium.custom.UserToRoleTO;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.domain.Role;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.util.ApplProperties;

/**
 * Mapper "роли пользователей системы" implementation.
 * @author IShafigullin
 * 
 */
public class RoleMapper extends JDBCMapper implements ru.masterdm.compendium.mapping.RoleMapper {

	protected static final String findByNameSqlString = "SELECT ID_ROLE id, NAME_ROLE name, ID_TYPE_PROCESS, ACTIVE FROM "
			+ ApplProperties.getDatasourceSchema() + ".roles WHERE LOWER(NAME_ROLE) matches LOWER(?)";

	protected static final String _loadString = "SELECT ID_ROLE id, NAME_ROLE name, ID_TYPE_PROCESS, ACTIVE FROM roles ";

	private static final String deleteLink_SQL = "DELETE FROM USER_IN_ROLE WHERE ID_ROLE = ? AND ID_USER = ? ";

	private static final String addLink_SQL = "INSERT INTO USER_IN_ROLE(ID_ROLE, ID_USER) VALUES (?,?) ";

	private static final String setStatusLink_SQL = "UPDATE USER_IN_ROLE SET STATUS = ? WHERE ID_ROLE = ? AND ID_USER = ?";
	
	private static final String deleteLinkCrCo_SQL = "DELETE FROM CC_USER_ROLE WHERE ID_ROLE = ? AND ID_USER = ? ";

	private static final String addLinkCrCo_SQL = "INSERT INTO CC_USER_ROLE(ID_ROLE, ID_USER) VALUES (?,?) ";
	
	private static final String setStatusLinkCrCo_SQL = "UPDATE CC_USER_ROLE SET STATUS = ? WHERE ID_ROLE = ? AND ID_USER = ?";
	
	private static final String deleteLinkRating_SQL = "DELETE FROM CR_USER_IN_ROLE  WHERE ID_ROLE = ? AND ID_USER = ? ";

	private static final String addLinkRating_SQL = "INSERT INTO CR_USER_IN_ROLE (ID_ROLE, ID_USER) VALUES (?,?) ";
	
	private static final String setStatusLinkRating_SQL = "UPDATE CR_USER_IN_ROLE  SET STATUS = ? WHERE ID_ROLE = ? AND ID_USER = ?";	

	/**
	 * 
	 */
	public RoleMapper() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.jdbc.JDBCMapper#createImpl(java.sql.Connection,
	 *      com.vtb.domain.VtbObject)
	 */
	protected Object createImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		throw new MappingException("createImpl not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.jdbc.JDBCMapper#findByPrimaryKeyImpl(java.sql.Connection,
	 *      com.vtb.domain.VtbObject)
	 */
	protected VtbObject findByPrimaryKeyImpl(Connection conn, VtbObject domainObjectWithKeyValues) throws SQLException,
			MappingException {
		throw new MappingException("findByPrimaryKeyImpl not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.jdbc.JDBCMapper#removeImpl(java.sql.Connection,
	 *      com.vtb.domain.VtbObject)
	 */
	protected void removeImpl(Connection conn, VtbObject domainObject) throws SQLException, MappingException {
		throw new MappingException("removeImpl not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.jdbc.JDBCMapper#updateImpl(java.sql.Connection,
	 *      com.vtb.domain.VtbObject)
	 */
	protected void updateImpl(Connection conn, VtbObject anObject) throws SQLException, MappingException {
		throw new MappingException("updateImpl not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#findById(java.lang.String)
	 */
	public Role findById(String roleId) throws NoSuchObjectException {
		throw new NoSuchObjectException("findById not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.Mapper#findAll()
	 */
	public ArrayList<?> findAll() throws MappingException {
		throw new MappingException("findAll not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#findByName(java.lang.String)
	 */
	public ArrayList<?> findByName(String roleName) throws MappingException {
		throw new MappingException("findByName not valid for this type");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#findByName(java.lang.String,
	 *      java.lang.String)
	 */
	public ArrayList<Role> findByName(String roleName, String orderBy) throws MappingException {
		ArrayList<Role> list = new ArrayList<Role>();
		Role role = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = null;
			if (orderBy != null && !orderBy.equals("")) {
				ps = conn.prepareStatement(findByNameSqlString + " order by " + orderBy);
			} else {
				ps = conn.prepareStatement(findByNameSqlString);
			}
			ps.setString(1, roleName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				role = activate(rs);
				list.add(role);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findByName code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	protected Role activate(ResultSet rs) throws SQLException {
		Role role = new Role(((BigDecimal) rs.getObject(1)).intValue(), rs.getString(2).trim());
		if(rs.getObject(3) != null){
			role.setProcessTypeID(((BigDecimal) rs.getObject(3)).intValue());
		}
		// role.setComment((rs.getString(3) != null) ? rs.getString(3).trim() :
		// "");
		// role.setSortOrder((Integer) rs.getObject(4));
		// role.setTaskComment((rs.getString(5) != null) ?
		// rs.getString(5).trim() : "");
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#findUserToRoleAccessList(java.lang.Integer,
	 *      java.lang.String)
	 */
	public ArrayList<UserToRoleTO> findUserToRoleAccessList(String operatorKey, String orderBy) throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.NAME_ROLE name, roles.ID_TYPE_PROCESS, roles.ACTIVE, type_process.DESCRIPTION_PROCESS FROM roles, type_process ");
			sb
					.append(" WHERE roles.ID_TYPE_PROCESS = type_process.ID_TYPE_PROCESS AND roles.ACTIVE = 1 AND ID_ROLE NOT IN (SELECT ID_ROLE FROM USER_IN_ROLE WHERE ID_USER = ?)");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, operatorKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				roleTO.setProcessName(rs.getString(5));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleAccessList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#findUserToRoleList(java.lang.Integer,
	 *      java.lang.String)
	 */
	public ArrayList<UserToRoleTO> findUserToRoleList(String operatorKey, String orderBy) throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.NAME_ROLE name, roles.ID_TYPE_PROCESS, roles.ACTIVE, user_in_role.STATUS, type_process.DESCRIPTION_PROCESS FROM roles, user_in_role, type_process ");
			sb
					.append(" WHERE roles.ID_ROLE = user_in_role.ID_ROLE AND roles.ID_TYPE_PROCESS = type_process.ID_TYPE_PROCESS AND user_in_role.ID_USER = ? ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, operatorKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				roleTO.setStatus(rs.getString(5));
				roleTO.setProcessName(rs.getString(6));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#addLinkUserToRole(java.lang.Integer,
	 *      String)
	 */
	public void addLinkUserToRole(String operatorKey, String roleKey) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(addLink_SQL);
			st.setString(1, roleKey);
			st.setString(2, operatorKey);
			if (st.executeUpdate() != 1) {
				String err = "addLinkUserToRole.operatorKey=" + operatorKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			if (se.getErrorCode() == -268) {
				throw new MappingException(se, "Данная связь присутствует!");
			}
			throw new MappingException(se, "SQLException addLinkUserToRole code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#deleteLinkUserToRole(java.lang.Integer,
	 *      String)
	 */
	public void deleteLinkUserToRole(String operatorKey, String roleKey) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(deleteLink_SQL);
			st.setString(1, roleKey);
			st.setString(2, operatorKey);
			if (st.executeUpdate() != 1) {
				String err = "deleteLinkUserToRole.operatorKey=" + operatorKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException deleteLinkUserToRole code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.RoleMapper#setStatusLinkUserToRole(java.lang.Integer,
	 *      String, String status)
	 */
	public void setStatusLinkUserToRole(String operatorKey, String roleKey, String status) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(setStatusLink_SQL);
			st.setString(1, status);
			st.setString(2, roleKey);
			st.setString(3, operatorKey);

			if (st.executeUpdate() != 1) {
				String err = "setStatusLinkUserToRole.operatorKey=" + operatorKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException setStatusLinkUserToRole code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	public ArrayList<UserToRoleTO> findUserToRoleAccessList(String operatorKey, String aProcessID, String orderBy)
			throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.NAME_ROLE name, roles.ID_TYPE_PROCESS, roles.ACTIVE, type_process.DESCRIPTION_PROCESS ");
			sb.append("FROM roles, type_process ");
			sb.append("WHERE roles.ID_TYPE_PROCESS = type_process.ID_TYPE_PROCESS AND roles.ACTIVE = 1 ");
			sb.append("AND ID_ROLE NOT IN (SELECT ID_ROLE FROM USER_IN_ROLE WHERE ID_USER = ?) ");
			// append processID clause:
			if (aProcessID != null && !aProcessID.trim().equalsIgnoreCase("ALL"))
				sb.append(" AND type_process.ID_TYPE_PROCESS = ? ");			
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, operatorKey);
			if (aProcessID != null && !aProcessID.trim().equalsIgnoreCase("ALL")){
				ps.setObject(2, aProcessID);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				roleTO.setProcessName(rs.getString(5));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleAccessList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(addLinkCrCo_SQL);
			st.setString(1, roleCrCoKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "addLinkUserToRoleCrCo.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			if (se.getErrorCode() == -268) {
				throw new MappingException(se, "Данная связь присутствует!");
			}
			throw new MappingException(se, "SQLException addLinkUserToRoleCrCo code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(deleteLinkCrCo_SQL);
			st.setString(1, roleCrCoKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "deleteLinkUserToRoleCrCo.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException deleteLinkUserToRoleCrCo code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public ArrayList<UserToRoleTO> findUserToRoleCrCoAccessList(String userKey, String orderBy)
			throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.ROLE_NAME name, null FROM CC_ROLE roles ");
			sb.append(" WHERE ID_ROLE NOT IN (SELECT ID_ROLE FROM CC_USER_ROLE WHERE ID_USER = ?) AND roles.ACTIVE = 1 ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				//roleTO.setProcessName(rs.getString(5));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleCrCoAccessList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public ArrayList<UserToRoleTO> findUserToRoleCrCoList(String userKey, String orderBy)
			throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.ROLE_NAME name, null, ur.STATUS FROM CC_ROLE roles, CC_USER_ROLE ur ");
			sb
					.append(" WHERE roles.ID_ROLE = ur.ID_ROLE AND ur.ID_USER = ? ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				roleTO.setStatus(rs.getString(4));
				//roleTO.setProcessName(rs.getString(6));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleCrCoList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,
			String status) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(setStatusLinkCrCo_SQL);
			st.setString(1, status);
			st.setString(2, roleCrCoKey);
			st.setString(3, userKey);

			if (st.executeUpdate() != 1) {
				String err = "setStatusLinkUserToRoleCrCo.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException setStatusLinkUserToRoleCrCo code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey)
			throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(addLinkRating_SQL);
			st.setString(1, roleRatingKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "addLinkUserToRoleRating.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			if (se.getErrorCode() == -268) {
				throw new MappingException(se, "Данная связь присутствует!");
			}
			throw new MappingException(se, "SQLException addLinkUserToRoleRating code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(deleteLinkRating_SQL);
			st.setString(1, roleRatingKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "deleteLinkUserToRoleRating.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException deleteLinkUserToRoleRating code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public ArrayList<UserToRoleTO> findUserToRoleRatingAccessList(String userKey, String orderBy) throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.NAME_ROLE name, null FROM CR_ROLES roles ");
			sb.append(" WHERE ID_ROLE NOT IN (SELECT ID_ROLE FROM CR_USER_IN_ROLE WHERE ID_USER = ?) AND roles.ACTIVE = 1 ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				//roleTO.setProcessName(rs.getString(5));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleRatingAccessList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public ArrayList<UserToRoleTO> findUserToRoleRatingList(String userKey, String orderBy)	throws MappingException {
		ArrayList<UserToRoleTO> ret = new ArrayList<UserToRoleTO>();
		UserToRoleTO roleTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT roles.ID_ROLE id, roles.NAME_ROLE name, null, ur.STATUS FROM CR_ROLES roles, CR_USER_IN_ROLE ur ");
			sb
					.append(" WHERE roles.ID_ROLE = ur.ID_ROLE AND ur.ID_USER = ? ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roleTO = new UserToRoleTO(activate(rs));
				roleTO.setStatus(rs.getString(4));
				//roleTO.setProcessName(rs.getString(6));
				ret.add(roleTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToRoleRatingList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void setStatusLinkUserToRoleRating(String userKey, String roleRatingKey, String status) throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(setStatusLinkRating_SQL);
			st.setString(1, status);
			st.setString(2, roleRatingKey);
			st.setString(3, userKey);

			if (st.executeUpdate() != 1) {
				String err = "setStatusLinkUserToRoleRating.userKey=" + userKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException setStatusLinkUserToRoleRating code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void setStatusAllLinkUserToRole(String operatorKey, String status) throws MappingException {
		ArrayList<UserToRoleTO> roles =null;
		roles = findUserToRoleList(operatorKey, null);
		for (UserToRoleTO user : roles){
			setStatusLinkUserToRole(operatorKey, user.getVo().getId().toString(), status);
		}
	}

	@Override
	public void setStatusAllLinkUserToRoleCrCo(String operatorKey, String status) throws MappingException {
		ArrayList<UserToRoleTO> roles =null;
		roles = findUserToRoleCrCoList(operatorKey, null);
		for (UserToRoleTO user : roles){
			setStatusLinkUserToRoleCrCo(operatorKey, user.getVo().getId().toString(), status);
		}
	}

	@Override
	public void setStatusAllLinkUserToRoleRating(String operatorKey, String status)	throws MappingException {
		ArrayList<UserToRoleTO> roles =null;
		roles = findUserToRoleRatingList(operatorKey, null);
		for (UserToRoleTO user : roles){
			setStatusLinkUserToRoleRating(operatorKey, user.getVo().getId().toString(), status);
		}
	}

	@Override
	public void addAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws MappingException {
		ArrayList<UserToRoleTO> roles = null;
		roles = findUserToRoleAccessList(operatorKey, processId, null);
		for (UserToRoleTO user: roles){
			addLinkUserToRole(operatorKey, user.getVo().getId().toString());
		}
	}

	@Override
	public void addAllLinkUserToRoleCrCo(String operatorKey) throws MappingException {
		ArrayList<UserToRoleTO> roles = null;
		roles = findUserToRoleCrCoAccessList(operatorKey, null);
		for (UserToRoleTO user: roles){
			addLinkUserToRoleCrCo(operatorKey, user.getVo().getId().toString());
		}
	}

	@Override
	public void addAllLinkUserToRoleRating(String operatorKey) throws MappingException {
		ArrayList<UserToRoleTO> roles = null;
		roles = findUserToRoleRatingAccessList(operatorKey, null);
		for (UserToRoleTO user : roles){
			addLinkUserToRoleRating(operatorKey, user.getVo().getId().toString());
		}
	}

	@Override
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey,	String processId) throws MappingException {
		//ArrayList<UserToRoleTO> processRoles = findUserToRoleAccessList(operatorKey, processId, null);
		ArrayList<UserToRoleTO> userRoles = findUserToRoleList(operatorKey, null);
		if (processId.equalsIgnoreCase("ALL")){
			for (UserToRoleTO userRole : userRoles){
				deleteLinkUserToRole(operatorKey, userRole.getVo().getId().toString());
			}
		} else {
			int processIntID = Integer.valueOf(processId).intValue();
			for (UserToRoleTO userRole : userRoles){
				if (userRole.getVo().getProcessTypeID().intValue() == processIntID){
					deleteLinkUserToRole(operatorKey, userRole.getVo().getId().toString());
				}
			}
		}
	}

	@Override
	public void deleteAllLinkUserToRoleCrCo(String operatorKey)	throws MappingException {
		ArrayList<UserToRoleTO> roles = findUserToRoleCrCoList(operatorKey, null);
		for (UserToRoleTO user : roles) {
			deleteLinkUserToRoleCrCo(operatorKey, user.getVo().getId().toString());
		}
	}

	@Override
	public void deleteAllLinkUserToRoleRating(String operatorKey) throws MappingException {
		ArrayList<UserToRoleTO> roles = findUserToRoleRatingList(operatorKey, null);
		for (UserToRoleTO user : roles) {
			deleteLinkUserToRoleRating(operatorKey, user.getVo().getId().toString());
		}
	}
}
