package ru.masterdm.compendium.mapping.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import ru.masterdm.compendium.custom.UserToRoleTO;
import ru.masterdm.compendium.domain.Role;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.entities.CcRoleJPA;
import ru.masterdm.compendium.entities.CcUserRoleJPA;
import ru.masterdm.compendium.entities.CrRoleJPA;
import ru.masterdm.compendium.entities.CrUserInRoleJPA;
import ru.masterdm.compendium.entities.CrUserInRoleJPAPK;
import ru.masterdm.compendium.entities.RoleJPA;
import ru.masterdm.compendium.entities.UserInRoleJPA;
import ru.masterdm.compendium.entities.UserInRoleJPAPK;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;

/**
 * Mapper "роли пользователей системы" implementation.
 * @author IShafigullin
 *
 */
public class RoleMapper extends DomainJPAMapper implements ru.masterdm.compendium.mapping.RoleMapper {

	@Override
	protected Object findJPAObjectMatching(VtbObject domainObject)
			throws MappingException {
		return null;
	}

	@Override
	public VtbObject map(Object jpaObject) throws MappingException {
		return null;
	}

	@Override
	public void addAllLinkUserToRoleByProcessSPO(String userKey, String processId) throws MappingException {
		try{
			ArrayList<UserToRoleTO> roles = (ArrayList<UserToRoleTO>)findUserToRoleAccessList(userKey, processId, null);
			for (UserToRoleTO user: roles){
				addLinkUserToRole(userKey, user.getVo().getId().toString());
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addAllLinkUserToRoleByProcessSPO " + e));
		}
	}

	@Override
	public void addAllLinkUserToRoleCrCo(String userKey) throws MappingException {
		try{
			ArrayList<UserToRoleTO> roles = findUserToRoleCrCoAccessList(userKey, null); 
			for (UserToRoleTO user: roles){
				addLinkUserToRoleCrCo(userKey, user.getVo().getId().toString());
			}
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addAllLinkUserToRoleCrCo " + e));
		}	}

	@Override
	public void addAllLinkUserToRoleRating(String userKey)throws MappingException {
		try{
			ArrayList<UserToRoleTO> roles = (ArrayList<UserToRoleTO>)findUserToRoleRatingAccessList(userKey, null);
			for (UserToRoleTO user : roles){
				addLinkUserToRoleRating(userKey, user.getVo().getId().toString());
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addAllLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void addLinkUserToRole(String userKey, String roleKey) throws MappingException {
		try {
			UserInRoleJPA jpa = new UserInRoleJPA(new UserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleKey)));
			getEntityMgr().persist(jpa);
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addLinkUserToRole " + e));
		}	}

	@Override
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws MappingException {
		try {
			CcUserRoleJPA jpa = new CcUserRoleJPA();
			jpa.setIdUser(Long.valueOf(userKey));
			CcRoleJPA role = getEntityMgr().find(CcRoleJPA.class, Long.valueOf(roleCrCoKey)); 
			jpa.setRole(role);
			getEntityMgr().persist(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey) throws MappingException {
		try {
			CrUserInRoleJPA jpa = new CrUserInRoleJPA(new CrUserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleRatingKey)));
			getEntityMgr().persist(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in addLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void deleteAllLinkUserToRoleByProcessSPO(String userKey,	String processId) throws MappingException {
		try{
			ArrayList<UserToRoleTO> userRoles = (ArrayList<UserToRoleTO>)findUserToRoleList(userKey, null);
			if (processId.equalsIgnoreCase("ALL")){
				for (UserToRoleTO userRole : userRoles){
					deleteLinkUserToRole(userKey, userRole.getVo().getId().toString());
				}
			} else {
				int processIntID = Integer.valueOf(processId).intValue();
				for (UserToRoleTO userRole : userRoles){
					if (userRole.getVo().getProcessTypeID().intValue() == processIntID){
						deleteLinkUserToRole(userKey, userRole.getVo().getId().toString());
					}
				}
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteAllLinkUserToRoleByProcessSPO " + e));
		}
	}

	@Override
	public void deleteAllLinkUserToRoleCrCo(String userKey)	throws MappingException {
		try{
			ArrayList<UserToRoleTO> roles = findUserToRoleCrCoList(userKey, null); 
			for (UserToRoleTO user : roles) {
				deleteLinkUserToRoleCrCo(userKey, user.getVo().getId().toString());
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteAllLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void deleteAllLinkUserToRoleRating(String userKey) throws MappingException {
		try{
			ArrayList<UserToRoleTO> roles = (ArrayList<UserToRoleTO>)findUserToRoleRatingList(userKey, null);
			for (UserToRoleTO user : roles) {
				deleteLinkUserToRoleRating(userKey, user.getVo().getId().toString());
			}
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteAllLinkUserToRoleRating " + e));
		}	}

	@Override
	public void deleteLinkUserToRole(String userKey, String roleKey) throws MappingException {
		try {
			UserInRoleJPA jpa = getEntityMgr().find(UserInRoleJPA.class, new UserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleKey)));
			getEntityMgr().remove(jpa);
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteLinkUserToRole " + e));
		}	}

	@Override
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws MappingException {
		try {
		    Query query = getEntityMgr().createNamedQuery("findLinkCrCoUserToRole");
		    query.setParameter("idUser", Long.valueOf(userKey)).setParameter("role_idRole", Long.valueOf(roleCrCoKey));
			CcUserRoleJPA jpa =  (CcUserRoleJPA) query.getSingleResult();;
			getEntityMgr().remove(jpa);
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws MappingException {
		try {
			CrUserInRoleJPA jpa = getEntityMgr().find(CrUserInRoleJPA.class, new CrUserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleRatingKey)));
			getEntityMgr().remove(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in deleteLinkUserToDepartmentCrCo " + e));
		}	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleAccessList(String userKey, String aProcessID, String orderBy) throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM RoleJPA c WHERE c.idRole NOT IN (SELECT c.pk.idRole FROM UserInRoleJPA c WHERE c.pk.idUser = :pk_idUser) AND c.active = 1";
		// append processID clause:
		if (aProcessID != null && !aProcessID.trim().equalsIgnoreCase("ALL"))
			sqlStr += " AND c.process.idTypeProcess = :idTypeProcess ";		
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter("pk_idUser", Long.valueOf(userKey));			
			if (aProcessID != null && !aProcessID.trim().equalsIgnoreCase("ALL"))
				query.setParameter("idTypeProcess", Long.valueOf(aProcessID));
			List<RoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<RoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				RoleJPA jpa = (RoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getIdRole(), jpa.getNameRole());
				UserToRoleTO to = new UserToRoleTO(vo);
				to.setProcessName(jpa.getProcess().getDescriptionProcess());
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleRatingList " + e));
		}
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleCrCoAccessList(String userKey, String orderBy) throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM CcRoleJPA c WHERE c.idRole NOT IN (SELECT c.role.idRole FROM CcUserRoleJPA c WHERE c.idUser = :idUser) AND c.active = 1";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter("idUser", Long.valueOf(userKey));
			List<CcRoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<CcRoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				CcRoleJPA jpa = (CcRoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getIdRole(), jpa.getRoleName());
				UserToRoleTO to = new UserToRoleTO(vo);
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleRatingList " + e));
		}	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleCrCoList(String userKey, String orderBy)	throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM CcUserRoleJPA c WHERE c.idUser = :idUser ";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter("idUser", Long.valueOf(userKey));
			List<CcUserRoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<CcUserRoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				CcUserRoleJPA jpa = (CcUserRoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getRole().getIdRole(), jpa.getRole().getRoleName());
				UserToRoleTO to = new UserToRoleTO(vo);
				to.setStatus(jpa.getStatus());
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleCrCoList " + e));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleList(String userKey, String orderBy)	throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM UserInRoleJPA c WHERE c.pk.idUser = :pk_idUser ";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter(1, Long.valueOf(userKey));
			List<UserInRoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<UserInRoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				UserInRoleJPA jpa = (UserInRoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getRole().getIdRole(), jpa.getRole().getNameRole());
				vo.setProcessTypeID((int)jpa.getRole().getProcess().getIdTypeProcess());
				UserToRoleTO to = new UserToRoleTO(vo);
				to.setStatus(jpa.getStatus());
				to.setProcessName(jpa.getRole().getProcess().getDescriptionProcess());
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleRatingList " + e));
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleRatingAccessList(String userKey, String orderBy) throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM CrRoleJPA c WHERE c.idRole NOT IN (SELECT c.pk.idRole FROM CrUserInRoleJPA c WHERE c.pk.idUser = :pk_idUser) AND c.active = 1";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter(1, Long.valueOf(userKey));
			List<CrRoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<CrRoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				CrRoleJPA jpa = (CrRoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getIdRole(), jpa.getNameRole());
				UserToRoleTO to = new UserToRoleTO(vo);
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleRatingList " + e));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToRoleTO> findUserToRoleRatingList(String userKey, String orderBy)	throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM CrUserInRoleJPA c WHERE c.pk.idUser = :pk_idUser ";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter(1, Long.valueOf(userKey));
			List<CrUserInRoleJPA> listJPA = query.getResultList();
			ArrayList<UserToRoleTO> list = new ArrayList<UserToRoleTO>();
			for (Iterator<CrUserInRoleJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				CrUserInRoleJPA jpa = (CrUserInRoleJPA) iterator.next();
				Role vo = new Role((int)jpa.getRole().getIdRole(), jpa.getRole().getNameRole());
				UserToRoleTO to = new UserToRoleTO(vo);
				to.setStatus(jpa.getStatus());
				list.add(to);
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToRoleRatingList " + e));
		}
	}

	@Override
	public void setStatusAllLinkUserToRole(String userKey, String status) throws MappingException {
		try {
			ArrayList<UserToRoleTO> roles = (ArrayList<UserToRoleTO>)findUserToRoleList(userKey, null);
			for (UserToRoleTO user : roles){
				setStatusLinkUserToRole(userKey, user.getVo().getId().toString(), status);
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setStatusAllLinkUserToRole " + e));
		}
	}

	@Override
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status) throws MappingException {
		try {
			ArrayList<UserToRoleTO> roles = findUserToRoleCrCoList(userKey, null);
			for (UserToRoleTO user : roles){
				setStatusLinkUserToRoleCrCo(userKey, user.getVo().getId().toString(), status);
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setStatusAllLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void setStatusAllLinkUserToRoleRating(String userKey, String status)	throws MappingException {
		try {
			ArrayList<UserToRoleTO> roles = (ArrayList<UserToRoleTO>) findUserToRoleRatingList(userKey, null);
			for (UserToRoleTO user : roles){
				setStatusLinkUserToRoleRating(userKey, user.getVo().getId().toString(), status);
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setStatusAllLinkUserToRoleRating " + e));
		}	}

	@Override
	public void setStatusLinkUserToRole(String userKey, String roleKey,	String status) throws MappingException {
		try {
			UserInRoleJPA jpa = getEntityMgr().find(UserInRoleJPA.class, new UserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleKey)));
			jpa.setStatus(status);
			getEntityMgr().merge(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setStatusLinkUserToRole " + e));
		}
	}

	@Override
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,	String status) throws MappingException {
		try {
		    Query query = getEntityMgr().createNamedQuery("findLinkCrCoUserToRole");
		    query.setParameter("idUser", Long.valueOf(userKey)).setParameter("role_idRole", Long.valueOf(roleCrCoKey));
			CcUserRoleJPA jpa =  (CcUserRoleJPA) query.getSingleResult();;
			jpa.setStatus(status);
			getEntityMgr().merge(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("MappingException caught in setStatusLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void setStatusLinkUserToRoleRating(String userKey, String roleRatingKey, String status) throws MappingException {
		try {
			CrUserInRoleJPA jpa = getEntityMgr().find(CrUserInRoleJPA.class, new CrUserInRoleJPAPK(Long.valueOf(userKey), Long.valueOf(roleRatingKey)));
			jpa.setStatus(status);
			getEntityMgr().merge(jpa);			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setStatusLinkUserToRoleRating " + e));
		}

	}

	@Override
	public void insert(VtbObject anObject) throws DuplicateKeyException,
			MappingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VtbObject anObject) throws NoSuchObjectException,
			MappingException {
		// TODO Auto-generated method stub
		
	}

}
