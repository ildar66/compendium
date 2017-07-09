package ru.masterdm.compendium.mapping.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.value.Name;
import ru.masterdm.compendium.value.Page;

/**
 * Mapper "пользователи системы" implementation.
 * @author IShafigullin
 *
 */
public class UserMapper extends JDBCMapper implements
		ru.masterdm.compendium.mapping.UserMapper {
	
	protected static final String findListByFilterSqlString = "SELECT u.ID_USER id, u.LOGIN login, u.SURNAME, u.NAME, u.PATRONYMIC, u.ID_DEPARTMENT, u.MAIL_USER eMail, d.fullName depName FROM "
			+ "USERS u, DEPARTMENTS d WHERE u.ID_DEPARTMENT = d.ID_DEPARTMENT ";
	protected static final String findUserTO_SqlString = "SELECT u.ID_USER id, u.LOGIN login, u.SURNAME, u.NAME, u.PATRONYMIC, u.ID_DEPARTMENT, u.MAIL_USER eMail, d.fullName depName FROM "
		+ "USERS u, DEPARTMENTS d WHERE u.ID_DEPARTMENT = d.ID_DEPARTMENT AND u.ID_USER = ?  ";
	protected static final String _loadString = "SELECT ID_USER, LOGIN, SURNAME, NAME, PATRONYMIC, ID_DEPARTMENT, MAIL_USER FROM USERS WHERE ID_USER = ?";

	protected static final String _createString = "INSERT INTO USERS (ID_USER, LOGIN, SURNAME, NAME, PATRONYMIC, ID_DEPARTMENT, MAIL_USER) VALUES (?, ?, ?, ?, ?, ?, ?)";

	protected static final String _removeString = "DELETE FROM USERS WHERE ID_USER = ?";

	protected static final String _storeString = "UPDATE USERS SET LOGIN = ?, SURNAME = ?, NAME = ?, PATRONYMIC = ?, ID_DEPARTMENT = ?, MAIL_USER = ? WHERE ID_USER = ?";	

	protected static final String findByLoginSqlString = "SELECT u.ID_USER id, u.LOGIN login, u.SURNAME, u.NAME, u.PATRONYMIC, u.ID_DEPARTMENT, u.MAIL_USER eMail, d.fullName depName FROM "
		+ "USERS u, DEPARTMENTS d WHERE u.ID_DEPARTMENT = d.ID_DEPARTMENT AND u.LOGIN = ?  ";

	@Override
	protected Object createImpl(Connection conn, VtbObject domainObject)
			throws SQLException, MappingException {
		Integer id = null;
		String login = null;
		String fieldFA = null;
		String fieldIM = null;
		String fieldOT = null;
		Integer departmentID = null;
		String eMail = null;

		if (domainObject instanceof User) {
			id = ((User) domainObject).getId();
			login = ((User) domainObject).getLogin();
			fieldFA = ((User) domainObject).getName().getLast();
			fieldIM = ((User) domainObject).getName().getFirst();
			fieldOT = ((User) domainObject).getName().getMiddle();
			departmentID = ((User) domainObject).getDepartmentID();
			eMail = ((User) domainObject).getEMail();

		} else {
			// update fails
			throw new DuplicateKeyException("Create Failed " + domainObject);
		}
		PreparedStatement ps = conn.prepareStatement(_createString);
		int i = 1;
		ps.setObject(i++, id);
		ps.setObject(i++, login);
		ps.setObject(i++, fieldFA);
		ps.setObject(i++, fieldIM);
		ps.setObject(i++, fieldOT);
		ps.setObject(i++, departmentID);
		ps.setObject(i++, eMail);
		int rows = ps.executeUpdate();
		if (rows == 1)
			return domainObject;
		else
			// failed
			throw new DuplicateKeyException("Create Failed " + domainObject);
	}

	@Override
	protected VtbObject findByPrimaryKeyImpl(Connection conn,
			VtbObject domainObjectWithKeyValues) throws SQLException,
			MappingException {
		User user = null;
		Integer userId = null;
		if (domainObjectWithKeyValues instanceof User) {
			userId = ((User) domainObjectWithKeyValues).getId();
		} else
			return null;
		PreparedStatement ps = conn.prepareStatement(_loadString);
		ps.setObject(1, userId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = activate(rs);
		}
		return user;
	}

	@Override
	protected void removeImpl(Connection conn, VtbObject domainObject)
			throws SQLException, MappingException {
		Integer aId = null;
		if (domainObject instanceof User) {
			aId = ((User) domainObject).getId();
		} else
			throw new MappingException("Removed Failed" + domainObject);
		PreparedStatement ps = conn.prepareStatement(_removeString);
		ps.setObject(1, aId);
		int rows = ps.executeUpdate();
		if (rows == 1)
			return;
		else
			// failed
			throw new MappingException("Remove Failed " + domainObject);

	}

	@Override
	protected void updateImpl(Connection conn, VtbObject domainObject)
			throws SQLException, MappingException {
		Integer id = null;
		String login = null;
		String fieldFA = null;
		String fieldIM = null;
		String fieldOT = null;
		Integer departmentID = null;
		String eMail = null;
		if (domainObject instanceof User) {
			id = ((User) domainObject).getId();
			login = ((User) domainObject).getLogin();
			fieldFA = ((User) domainObject).getName().getLast();
			fieldIM = ((User) domainObject).getName().getFirst();
			fieldOT = ((User) domainObject).getName().getMiddle();
			departmentID = ((User) domainObject).getDepartmentID();
			eMail = ((User) domainObject).getEMail();
		} else {
			// update fails
			throw new MappingException("Update Failed " + domainObject);
		}
		PreparedStatement ps = conn.prepareStatement(_storeString);

		int i = 1;
		ps.setObject(i++, login);
		ps.setObject(i++, fieldFA);
		ps.setObject(i++, fieldIM);
		ps.setObject(i++, fieldOT);
		ps.setObject(i++, departmentID);
		ps.setObject(i++, eMail);

		ps.setObject(i++, id);
		int rows = ps.executeUpdate();
		if (rows == 1)
			return;
		else
			// failed
			throw new MappingException("Update Failed " + domainObject);
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.UserMapper#findUserTO(java.lang.String)
	 */
	public UserTO findUserTO(String userID) throws MappingException {
		UserTO to = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer(findUserTO_SqlString);
		try {
			conn = getConnection();
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				to = activateTO(rs);
			}
			if (to == null)
				throw new NoSuchObjectException("No object found");
			return to;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserTO code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vtb.mapping.UserMapper#findListByFilter(int,
	 * java.lang.String, java.lang.String)
	 */
	public ArrayList<UserTO> findListByFilter(int searchFilter, String searchStr,
			String orderBy) throws MappingException {
		ArrayList<UserTO> list = new ArrayList<UserTO>();
		UserTO to = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer(findListByFilterSqlString);
		if (searchFilter == 1) {
			sb.append(" AND LOWER(LOGIN) like LOWER(?) ");
		} else if (searchFilter == 2) {
			sb.append(" AND LOWER(SURNAME) like LOWER(?) ");
		} else if (searchFilter == 0) {
			sb.append(" AND (LOWER(SURNAME) like LOWER(?) OR LOWER(LOGIN) like LOWER(?)) ");
		}
		try {
			conn = getConnection();
			PreparedStatement ps = null;
			if (orderBy != null && !orderBy.equals("")) {
				sb.append(" order by " + orderBy);
			}
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, searchStr);
			if (searchFilter == 0) {
				ps.setString(2, searchStr);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				to = activateTO(rs);
				list.add(to);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se,
					"SQLException findListByFilter code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}
	
	public Page findPageByFilter(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws MappingException {
		Connection conn = null;
		UserTO to = null;
		Page ret = null;
		StringBuffer sb = new StringBuffer(findListByFilterSqlString);
		if (searchFilter == 1) {
			sb.append(" AND LOWER(LOGIN) like LOWER(?) ");
		} else if (searchFilter == 2) {
			sb.append(" AND LOWER(SURNAME) like LOWER(?) ");
		} else if (searchFilter == 3) {
			sb.append(" AND LOWER(fullName) like LOWER(?) ");
		} else if (searchFilter == 4) {
			sb.append(" AND LOWER(MAIL_USER) like LOWER(?) ");			
		} else if (searchFilter == 0) {
			sb
					.append(" AND (LOWER(SURNAME) like LOWER(?) OR LOWER(LOGIN) like LOWER(?)) ");
		}
		try {
			conn = getConnection();
			if (orderBy != null && !orderBy.equals("")) {
				sb.append(" order by " + orderBy);
			}			
			PreparedStatement ps = conn.prepareStatement(sb.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, searchStr);
			if (searchFilter == 0) {
				ps.setString(2, searchStr);
			}
			ResultSet rs = ps.executeQuery();
			if (start >= 0 && rs.absolute(start + 1)) {
				boolean hasNext = false;
				List<UserTO> items = new ArrayList<UserTO>();
				do {
					to = activateTO(rs);
					items.add(to);
				} while ((hasNext = rs.next()) && (--count > 0));
				ret = new Page(items, start, hasNext);
			} else {
				ret = Page.EMPTY_PAGE;
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.err);
			throw new MappingException(se,
					"SQLException findListByFilter code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}
	

	protected UserTO activateTO(ResultSet rs) throws SQLException {
		int i = 1;
		User vo = new User(((BigDecimal) rs.getObject(i++)).intValue(), rs
				.getString(i++));
		Name name = new Name(rs.getString(i++), rs.getString(i++), rs
				.getString(i++));
		vo.setName(name);
		if (rs.getObject(i) != null) {
			vo.setDepartmentID(((BigDecimal) rs.getObject(i++)).intValue());
		}
		vo.setEMail(rs.getString(i++));
		UserTO to = new UserTO(vo);
		to.setDepName(rs.getString(i++));
		return to;
	}
	
	protected User activate(ResultSet rs) throws SQLException {
		int i = 1;
		User user = new User(((BigDecimal) rs.getObject(i++)).intValue(), rs.getString(i++));
		Name name = new Name(rs.getString(i++), rs.getString(i++), rs
				.getString(i++));
		user.setName(name);
		if (rs.getObject(i) != null) {
			user.setDepartmentID(((BigDecimal) rs.getObject(i++)).intValue());
		}
		user.setEMail(rs.getString(i++));
		return user;
	}	

	@SuppressWarnings("unchecked")
	public ArrayList findAll() throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTO findUserByLogin(String aLogin)
			throws MappingException {
		UserTO to = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer(findByLoginSqlString);
		try {
			conn = getConnection();
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, aLogin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				to = activateTO(rs);
			}
			//if (to == null)
				//throw new NoSuchObjectException("No object found");
			return to;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserByLogin code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public ArrayList<UserToDepartmentTO> findUserToDepartmentCrCoList(
			String userKey, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,
			Integer[] departmentCrCoKeys) {
		// TODO Auto-generated method stub
		
	}
}
