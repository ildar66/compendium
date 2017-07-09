package ru.masterdm.compendium.mapping.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;

/**
 * Mapper "филиалов" implementation.
 * @author IShafigullin
 *
 */
public class DepartmentMapper extends JDBCMapper implements ru.masterdm.compendium.mapping.DepartmentMapper {
	protected static final String findByNameSqlString = "SELECT ID_DEPARTMENT id, SHORTNAME shortName, FULLNAME fullName, DEP_ID codeDep, CB_CD code FROM "
			+ "DEPARTMENTS WHERE LOWER(SHORTNAME) like LOWER(?) OR LOWER(FULLNAME) like LOWER(?) ";

	protected static final String _loadString = "SELECT ID_DEPARTMENT id, SHORTNAME shortName, FULLNAME fullName, DEP_ID codeDep, CB_CD code FROM "
			+ "DEPARTMENTS WHERE ID_DEPARTMENT = ?";

	protected static final String _createString = "INSERT INTO "
			+ "DEPARTMENTS (ID_DEPARTMENT, SHORTNAME, FULLNAME, DEP_ID, CB_CD) VALUES (?, ?, ?, ?, ?)";

	protected static final String _removeString = "DELETE FROM "
			+ "DEPARTMENTS  WHERE ID_DEPARTMENT = ?";

	protected static final String _storeString = "UPDATE "
			+ "DEPARTMENTS  SET SHORTNAME = ?, FULLNAME = ?, DEP_ID = ?, CB_CD = ? WHERE ID_DEPARTMENT = ?";
	
	private static final String deleteLinkCrCo_SQL = "DELETE FROM CC_USER_DEP WHERE ID_DEPARTMENT = ? AND ID_USER = ? ";

	private static final String addLinkCrCo_SQL = "INSERT INTO CC_USER_DEP(ID_DEPARTMENT, ID_USER) VALUES (?,?) ";	

	@Override
	protected Object createImpl(Connection conn, VtbObject domainObject)
			throws SQLException, MappingException {
		Integer id = null;
		String shortName = null;
		String fullName = null;
		Integer codeDep = null;
		Integer code = null;
		if (domainObject instanceof Department) {
			id = ((Department) domainObject).getId();
			shortName = ((Department) domainObject).getShortName();
			fullName = ((Department) domainObject).getFullName();
			codeDep = ((Department) domainObject).getCodeDep();
			code = ((Department) domainObject).getCode();
		} else {
			// update fails
			throw new DuplicateKeyException("Create Failed " + domainObject);
		}
		PreparedStatement ps = conn.prepareStatement(_createString);
		ps.setObject(1, id);
		ps.setObject(2, shortName);
		ps.setObject(3, fullName);
		ps.setObject(4, codeDep);
		ps.setObject(5, code);		
		
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
		Department departments = null;
		Integer departmentsId = null;
		if (domainObjectWithKeyValues instanceof Department) {
			departmentsId = ((Department) domainObjectWithKeyValues).getId();
		} else
			return null;
		PreparedStatement ps = conn.prepareStatement(_loadString);
		ps.setObject(1, departmentsId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			departments = activate(rs);
		}
		return departments;
	}

	@Override
	protected void removeImpl(Connection conn, VtbObject domainObject)
			throws SQLException, MappingException {
		Integer aId = null;
		if (domainObject instanceof Department) {
			aId = ((Department) domainObject).getId();
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
		String shortName = null;
		String fullName = null;
		Integer codeDep = null;
		Integer code = null;
		if (domainObject instanceof Department) {
			id = ((Department) domainObject).getId();
			shortName = ((Department) domainObject).getShortName();
			fullName = ((Department) domainObject).getFullName();
			codeDep = ((Department) domainObject).getCodeDep();
			code = ((Department) domainObject).getCode();
		} else {
			// update fails
			throw new MappingException("Update Failed " + domainObject);
		}
		PreparedStatement ps = conn.prepareStatement(_storeString);
		ps.setObject(1, shortName);
		ps.setObject(2, fullName);
		ps.setObject(3, codeDep);
		ps.setObject(4, code);
		ps.setObject(5, id);
		
		int rows = ps.executeUpdate();
		if (rows == 1)
			return;
		else
			// failed
			throw new MappingException("Update Failed " + domainObject);
	}

	@SuppressWarnings("unchecked")
	public ArrayList findByName(String name, String orderBy)
			throws MappingException {
		ArrayList<Department> list = new ArrayList<Department>();
		Department departments = null;
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
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				departments = activate(rs);
				list.add(departments);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findByName code="
					+ se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	protected Department activate(ResultSet rs) throws SQLException {
		Department department = new Department(
				((BigDecimal) rs.getObject(1)).intValue(), rs.getString(2), rs
						.getString(3));
		if(rs.getObject(4) != null){
			department.setCodeDep(((BigDecimal)rs.getObject(4)).intValue());
		}
		if(rs.getObject(5) != null){
			department.setCode(((BigDecimal)rs.getObject(5)).intValue());
		}
		return department;
	}

	@SuppressWarnings("unchecked")
	public ArrayList findAll() throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void addLinkUserToDepartmentCrCo(String departmentKey, String userKey)
			throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(addLinkCrCo_SQL);
			st.setString(1, departmentKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "addLinkUserToDepartmentCrCo.departmentKey=" + departmentKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			if (se.getErrorCode() == -268) {
				throw new MappingException(se, "Данная связь присутствует!");
			}
			throw new MappingException(se, "SQLException addLinkUserToDepartmentCrCo code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void deleteLinkUserToDepartmentCrCo(String departmentKey, String userKey)
			throws MappingException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(deleteLinkCrCo_SQL);
			st.setString(1, departmentKey);
			st.setString(2, userKey);
			if (st.executeUpdate() != 1) {
				String err = "deleteLinkUserToDepartmentCrCo.departmentKey=" + departmentKey + " failed";
				throw new MappingException(err);
			}
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException deleteLinkUserToDepartmentCrCo code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}
	
	@Override
	public ArrayList<UserToDepartmentTO> findUserToDepartmentCrCoList(String userKey, String orderBy)
			throws MappingException {
		ArrayList<UserToDepartmentTO> ret = new ArrayList<UserToDepartmentTO>();
		UserToDepartmentTO departmentTO = null;
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer sb = new StringBuffer(
					"SELECT dep.ID_DEPARTMENT id, SHORTNAME shortName, FULLNAME fullName, DEP_ID codeDep, CB_CD code FROM DEPARTMENTS dep, CC_USER_DEP ud ");
			sb
					.append(" WHERE dep.ID_DEPARTMENT = ud.ID_DEPARTMENT AND ud.ID_USER = ? ");
			// append order by clause:
			if (orderBy != null && !orderBy.equals(""))
				sb.append(" order by " + orderBy);
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, userKey);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				departmentTO = new UserToDepartmentTO(activate(rs));
				//departmentTO.setStatus(rs.getString(4));
				ret.add(departmentTO);
			}
			return ret;
		} catch (SQLException se) {
			se.printStackTrace(System.out);
			throw new MappingException(se, "SQLException findUserToDepartmentCrCoList code=" + se.getErrorCode());
		} finally {
			close(conn);
		}
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,
			Integer[] departmentCrCoKeys) throws MappingException {
		ArrayList<UserToDepartmentTO> listAllDepCrCo = findUserToDepartmentCrCoList(userKey, null);
		java.util.Iterator<UserToDepartmentTO> iter = listAllDepCrCo.listIterator();
		while (iter.hasNext()) {
			UserToDepartmentTO to = (UserToDepartmentTO) iter.next();
			Integer depCrCo = to.getVo().getId();
			deleteLinkUserToDepartmentCrCo("" + depCrCo, userKey);
		}
		for (int i = 0; i < departmentCrCoKeys.length; i++) {
			addLinkUserToDepartmentCrCo("" + departmentCrCoKeys[i], userKey);
		}
	}

	@Override
	public DepartmentPar[] getDepartmentParAll() throws MappingException {
		throw new MappingException("getDepartmentParAll not valid for this type");
	}	

}
