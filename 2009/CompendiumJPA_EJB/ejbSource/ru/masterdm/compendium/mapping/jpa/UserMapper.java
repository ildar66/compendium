/**
 * 
 */
package ru.masterdm.compendium.mapping.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.entities.CcUserDepJPA;
import ru.masterdm.compendium.entities.CcUserDepJPAPK;
import ru.masterdm.compendium.entities.DepartmentJPA;
import ru.masterdm.compendium.entities.UserJPA;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.mapping.MapperFactory;
import ru.masterdm.compendium.value.Name;
import ru.masterdm.compendium.value.Page;

/**
 * Mapper "пользователи системы" implementation.
 * @author IShafigullin
 *
 */
public class UserMapper extends DomainJPAMapper implements ru.masterdm.compendium.mapping.UserMapper {
	protected static final String findListByFilterSqlString = "SELECT u.ID_USER id, u.LOGIN login, u.SURNAME, u.NAME, u.PATRONYMIC, u.ID_DEPARTMENT, u.MAIL_USER eMail, d.fullName depName FROM "
		+ "USERS u, DEPARTMENTS d WHERE u.ID_DEPARTMENT = d.ID_DEPARTMENT ";

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#findJPAObjectMatching(ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	public Object findJPAObjectMatching(VtbObject domainObject)	throws MappingException {
		User vo = (User)domainObject;
		return getEntityMgr().find(UserJPA.class, (long)vo.getId());
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#map(java.lang.Object)
	 */
	@Override
	public VtbObject map(Object input) throws MappingException {
		UserJPA jpa = (UserJPA)input;
		User retVO = new User((int)jpa.getIdUser(), jpa.getLogin());
		Name name = new Name(jpa.getSurname(), jpa.getName(), jpa.getPatronymic());
		retVO.setName(name);
		if(jpa.getIdDepartment() != null){
			retVO.setDepartmentID(jpa.getIdDepartment().intValue());
		}
		retVO.setEMail(jpa.getMailUser());
		return retVO; 
	}

	private Object map(Object result, VtbObject domainObject)
			throws MappingException {
		User vo = (User)domainObject;
		UserJPA jpa = (UserJPA)result;
		jpa.setIdUser(vo.getId());
		jpa.setLogin(vo.getLogin());
		Name name = vo.getName();
		if(name != null){
			jpa.setSurname(name.getLast());
			jpa.setName(name.getFirst());
			jpa.setPatronymic(name.getMiddle());
		}
		if(vo.getDepartmentID() != null){
			jpa.setIdDepartment(new BigDecimal(vo.getDepartmentID()));
		}
		jpa.setMailUser(vo.getEMail());
		return jpa;
	}

	@Override
	public void insert(VtbObject anObject) throws DuplicateKeyException, MappingException {
		UserJPA jpa = (UserJPA)map(new UserJPA(), anObject);
		getEntityMgr().persist(jpa);		
	}

	@Override
	public void update(VtbObject anObject) throws NoSuchObjectException, MappingException {
		UserJPA jpa = (UserJPA)findJPAObjectMatching(anObject);
		getEntityMgr().merge(map(jpa, anObject));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page findPageByFilter(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws MappingException {
		// TODO Auto-generated method stub
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
			sb.append(" AND (LOWER(SURNAME) like LOWER(?) OR LOWER(LOGIN) like LOWER(?)) ");
		}
		if (orderBy != null && !orderBy.equals("")) {
			sb.append(" order by " + orderBy);
		}
		Query query = getEntityMgr().createNativeQuery(sb.toString()).setMaxResults(count).setFirstResult(start);
		query.setParameter(1, searchStr);
		if (searchFilter == 0) {
			query.setParameter(2, searchStr);
		}		
		List<Object[]> jpaList = query.getResultList();
		List<UserTO> items = new ArrayList<UserTO>();
		for (Iterator<Object[]> iterator = jpaList.iterator(); iterator.hasNext();) {
			Object[] jpa = (Object[]) iterator.next();
			to = activateTO(jpa);
			items.add(to);
		}
		if(items.size() > 0){
			ret = new Page(items, start, items.size() == count);//TODO
		}else{
			ret = Page.EMPTY_PAGE;
		}
		return ret;
	}
	
	private UserTO activateTO(Object[] rs) {
		int i = 0;
		User vo = new User(((BigDecimal) rs[i++]).intValue(), (String)rs[i++]);
		Name name = new Name((String)rs[i++], (String)rs[i++], (String)rs[i++]);
		vo.setName(name);
		if (rs[i] != null) {
			vo.setDepartmentID(((BigDecimal) rs[i++]).intValue());
		}
		vo.setEMail((String)rs[i++]);
		UserTO to = new UserTO(vo);
		to.setDepName((String)rs[i++]);
		return to;
	}	

	@Override
	public UserTO findUserByLogin(String login) throws MappingException {
		UserTO to = null;
		try {
			Query query = getEntityMgr().createNamedQuery("findUserByLogin");
			query.setParameter("login", login);
			UserJPA jpa = (UserJPA)query.getSingleResult();
			User vo = (User)map(jpa);
			to = new UserTO(vo);
			DepartmentJPA jpaDep = getEntityMgr().find(DepartmentJPA.class, (long)vo.getDepartmentID());			
			to.setDepName(jpaDep.getShortName());
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserByLogin " + e));
		}
		return to;
	}

	@Override
	public UserTO findUserTO(String userID) throws MappingException {
		UserTO to = null;
		try {
			User vo = (User)findByPrimaryKey(new User(Integer.valueOf(userID)));
			to = new UserTO(vo);
			DepartmentJPA jpaDep = getEntityMgr().find(DepartmentJPA.class, (long)vo.getDepartmentID());			
			to.setDepName(jpaDep.getShortName());			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserTO " + e));
		}
		return to;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserToDepartmentTO> findUserToDepartmentCrCoList(String userKey, String orderBy) throws MappingException {
		ArrayList<UserToDepartmentTO> list = new ArrayList<UserToDepartmentTO>();
		try {
			Query query = getEntityMgr().createNamedQuery("findCcDepByUser");
			query.setParameter("pk_idUser", Long.valueOf(userKey));
			List<CcUserDepJPA> listJPA = query.getResultList();
			DomainJPAMapper mapper = (DomainJPAMapper) MapperFactory.getReserveMapperFactory().getMapper(Department.class);	
			for (Iterator<CcUserDepJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				CcUserDepJPA jpa = (CcUserDepJPA) iterator.next();
				DepartmentJPA depJPA = getEntityMgr().find(DepartmentJPA.class, jpa.getPk().getIdDepartment());
				Department vo = (Department)mapper.map(depJPA);
				UserToDepartmentTO to = new UserToDepartmentTO(vo);
				list.add(to);
			}
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in findUserToDepartmentCrCoList " + e));
		}
		return list;
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,	Integer[] departmentCrCoKeys) throws MappingException {
		try {
			ArrayList<UserToDepartmentTO> listAllDepCrCo = findUserToDepartmentCrCoList(userKey, null);
			java.util.Iterator<UserToDepartmentTO> iter = listAllDepCrCo.listIterator();
			while (iter.hasNext()) {
				UserToDepartmentTO to = (UserToDepartmentTO) iter.next();
				long depCrCo = (long)to.getVo().getId();
				CcUserDepJPA jpa = getEntityMgr().find(CcUserDepJPA.class, new CcUserDepJPAPK(Long.valueOf(userKey), depCrCo));
				getEntityMgr().remove(jpa);
			}
			for (int i = 0; i < departmentCrCoKeys.length; i++) {
				CcUserDepJPA jpa = new CcUserDepJPA();
				CcUserDepJPAPK pk = new CcUserDepJPAPK(Long.valueOf(userKey), (long)departmentCrCoKeys[i]);
				jpa.setPk(pk);
				getEntityMgr().persist(jpa);
			}			
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in setLinkUserToDepartmentCrCo " + e));
		}
	}
}
