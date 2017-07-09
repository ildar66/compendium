package ru.masterdm.compendium.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.domain.ProcessType;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.mapping.Mapper;
import ru.masterdm.compendium.mapping.MapperFactory;
import ru.masterdm.compendium.mapping.ProcessTypeMapper;
import ru.masterdm.compendium.mapping.UserMapper;
import ru.masterdm.compendium.service.EJBUserRemote;
import ru.masterdm.compendium.service.EJBUserService;
import ru.masterdm.compendium.value.Page;

/**
 * Application Model implementations for VTB manipulation services for Users.
 * Session Bean implementation class EJBUserBean
 */
@Stateless
@PersistenceContext(name="CompendiumJPA", unitName = "CompendiumJPA", type = PersistenceContextType.TRANSACTION)
public class EJBUserBean implements EJBUserService, EJBUserRemote {

	@Override
	public ArrayList<?> findProcessTypeList(String nameProcessType, String orderBy)
			throws ModelException {
		ArrayList<?> list = null;
		try {
			ProcessTypeMapper mapper = (ProcessTypeMapper) MapperFactory.getReserveMapperFactory().getMapper(ProcessType.class);
			list = mapper.findByName(nameProcessType, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findProcessTypeList " + e));
		}
		return list;
	}

	@Override
	public UserTO findUserTO(String userID) throws ModelException {
		UserTO to = null;
		try {
			UserMapper mapper = (UserMapper) MapperFactory.getReserveMapperFactory().getMapper(User.class);
			to = mapper.findUserTO(userID);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserTO " + e));
		}
		return to;
	}

	@Override
	public void insertUser(User newUser) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(User.class);
			mapper.insert(newUser);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException caught in insertUser " + e);
		}
	}

	@Override
	public User getUser(User vo) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(User.class);
			return (User)mapper.findByPrimaryKey(vo);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException caught in getUser" + e);
		}
	}

	@Override
	public void deleteUser(User vo) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(User.class);
			mapper.remove(vo);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in deleteUser " + e));
		}
	}

	@Override
	public void updateUser(User vo) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(User.class);
			mapper.update(vo);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in updateUser " + e));
		}
	}

	@Override
	public ArrayList<UserToDepartmentTO> findUserToDepartmentCrCoList(String userKey, String orderBy)
			throws ModelException {
		try {
			UserMapper mapper = (UserMapper) MapperFactory.getReserveMapperFactory().getMapper(User.class);
			return mapper.findUserToDepartmentCrCoList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException=" + e);
		}
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,	Integer[] departmentCrCoKeys) throws ModelException {
		try {
			UserMapper mapper = (UserMapper) MapperFactory.getReserveMapperFactory().getMapper(User.class);
			mapper.setLinkUserToDepartmentCrCo(userKey, departmentCrCoKeys);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException=" + e);
		}		
	}

	@Override
	public UserTO findUserByLogin(String aLogin) throws ModelException {
		try {
			UserMapper mapper = (UserMapper) MapperFactory.getReserveMapperFactory().getMapper(User.class);
			return (UserTO)mapper.findUserByLogin(aLogin);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException=" + e);
		}
	}

	public Page findUserPage(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws ModelException {
		Page page = null;
		try {
			//UserMapper mapper = (UserMapper) MapperFactory.getSystemMapperFactory().getMapper(User.class);
			UserMapper mapper = (UserMapper) MapperFactory.getReserveMapperFactory().getMapper(User.class);
			page = mapper.findPageByFilter(searchFilter, searchStr, start, count, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserPage " + e));
		}
		return page;
	}
}
