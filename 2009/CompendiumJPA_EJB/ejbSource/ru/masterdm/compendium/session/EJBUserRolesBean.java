package ru.masterdm.compendium.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ru.masterdm.compendium.domain.Role;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.mapping.MapperFactory;
import ru.masterdm.compendium.mapping.RoleMapper;
import ru.masterdm.compendium.service.EJBUserRolesRemote;
import ru.masterdm.compendium.service.EJBUserRolesService;

/**
 * Application Model implementations for VTB manipulation services for User roles.
 * Session Bean implementation class EJBUserBean
 */
@Stateless
@PersistenceContext(name="CompendiumJPA", unitName = "CompendiumJPA", type = PersistenceContextType.TRANSACTION)
public class EJBUserRolesBean implements EJBUserRolesService, EJBUserRolesRemote {
	
	@Override
	public ArrayList<?> findUserToRoleAccessList(String userKey, String processID, String orderBy) throws ModelException {
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			list = mapper.findUserToRoleAccessList(userKey, processID, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserList " + e));
		}
		return list;
	}

	@Override
	public ArrayList<?> findUserToRoleList(String userKey, String orderBy)  throws ModelException{
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			list = mapper.findUserToRoleList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserList " + e));
		}
		return list;
	}

	@Override
	public void addLinkUserToRole(String userKey, String roleKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addLinkUserToRole(userKey, roleKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void deleteLinkUserToRole(String userKey, String roleKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteLinkUserToRole(userKey, roleKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in deleteLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void setStatusLinkUserToRole(String userKey, String roleKey,	String status) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusLinkUserToRole(userKey, roleKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addLinkUserToRoleCrCo(userKey, roleCrCoKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addLinkUserToRoleRating " + e));
		}		
	}

	@Override
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteLinkUserToRoleCrCo(userKey, roleCrCoKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in deleteLinkUserToRoleRating " + e));
		}		
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoAccessList(String userKey, String orderBy)  throws ModelException{
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			list = mapper.findUserToRoleCrCoAccessList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserToRoleCrCoAccessList " + e));
		}
		return list;
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoList(String userKey, String orderBy)  throws ModelException{
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			list = mapper.findUserToRoleCrCoList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserToRoleCrCoList " + e));
		}
		return list;
	}

	@Override
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,	String status)  throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusLinkUserToRoleCrCo(userKey, roleCrCoKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusLinkUserToRoleRating " + e));
		}		
	}

	@Override
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException{
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addLinkUserToRoleRating(userKey, roleRatingKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteLinkUserToRoleRating(userKey, roleRatingKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in deleteLinkUserToRoleRating " + e));
		}		
	}

	@Override
	public ArrayList<?> findUserToRoleRatingList(String userKey, String orderBy) throws ModelException {
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			list = mapper.findUserToRoleRatingList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserToRoleRatingList " + e));
		}
		return list;
	}

	@Override
	public ArrayList<?> findUserToRoleRatingAccessList(String userKey, String orderBy) throws ModelException {
		ArrayList<?> list = null;
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class); 
			list = mapper.findUserToRoleRatingAccessList(userKey, orderBy);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in findUserToRoleRatingAccessList " + e));
		}
		return list;
	}

	@Override
	public void setStatusLinkUserToRoleRating(String userKey,
			String roleRatingKey, String status) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusLinkUserToRoleRating(userKey, roleRatingKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void setStatusAllLinkUserToRole(String userKey, String status) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusAllLinkUserToRole(userKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusAllLinkUserToRole " + e));
		}		
	}

	@Override
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusAllLinkUserToRoleCrCo(userKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusAllLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void setStatusAllLinkUserToRoleRating(String userKey, String status)	throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.setStatusAllLinkUserToRoleRating(userKey, status);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in setStatusLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void addAllLinkUserToRoleByProcessSPO(String userKey, String processId) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addAllLinkUserToRoleByProcessSPO(userKey, processId);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addAllLinkUserToRoleByProcessSPO " + e));
		}
	}

	@Override
	public void addAllLinkUserToRoleCrCo(String userKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addAllLinkUserToRoleCrCo(userKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addAllLinkUserToRoleCrCo " + e));
		}
	}

	@Override
	public void addAllLinkUserToRoleRating(String userKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.addAllLinkUserToRoleRating(userKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addAllLinkUserToRoleRating " + e));
		}	
	}

	@Override
	public void deleteAllLinkUserToRoleByProcessSPO(String userKey,	String processId) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteAllLinkUserToRoleByProcessSPO(userKey, processId);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addAllLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void deleteAllLinkUserToRoleCrCo(String userKey)	throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteAllLinkUserToRoleCrCo(userKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in addAllLinkUserToRoleRating " + e));
		}
	}

	@Override
	public void deleteAllLinkUserToRoleRating(String userKey) throws ModelException {
		try {
			RoleMapper mapper = (RoleMapper) MapperFactory.getReserveMapperFactory().getMapper(Role.class);
			mapper.deleteAllLinkUserToRoleRating(userKey);
		} catch (MappingException e) {
			throw new ModelException(e, ("MappingException caught in deleteAllLinkUserToRoleRating " + e));
		}
	}
}
