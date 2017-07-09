/**
 * 
 */
package ru.masterdm.compendium.model.web;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.service.EJBCompendiumService;
import ru.masterdm.compendium.service.EJBUserRolesService;
import ru.masterdm.compendium.service.EJBUserService;
import ru.masterdm.compendium.util.EjbLocator;
import ru.masterdm.compendium.value.Page;

/**
 * Compendium ActionProcessor Implementation.
 * Application Model interface for VTB local manipulation services.
 * @author IShafigullin
 * 
 */
public class CompendiumActionProcessorImpl implements CompendiumActionProcessor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
	.getLogger(CompendiumActionProcessorImpl.class.getName());
	
	/**
	 * Access (create) an instance of the EJBCompendiumService Facade
	 * @throws ModelException 
	 * 
	 * @returns a new Compendium session facade bean
	 */
	protected EJBCompendiumService getEJBCompendiumServiceFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBCompendiumService.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBCompendiumService not found");
		}
	}
	
	/**
	 * Access (create) an instance of the EJBUserService Facade
	 * @throws ModelException 
	 * 
	 * @returns a new UserRoles session facade bean
	 */
	protected EJBUserRolesService getEJBUserRolesServiceFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBUserRolesService.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBUserService not found");
		}
	}	
	
	/**
	 * Access (create) an instance of the EJBUserService Facade
	 * @throws ModelException 
	 * 
	 * @returns a new User session facade bean
	 */
	protected EJBUserService getEJBUserServiceFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBUserService.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBUserService not found");
		}
	}	

	@Override
	public ArrayList<?> findProcessTypeList(String nameProcessType, String orderBy)
			throws ModelException {
		return getEJBUserServiceFacade().findProcessTypeList(nameProcessType, orderBy);
	}

	@Override
	public void addLinkUserToRole(String operatorKey, String roleKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().addLinkUserToRole(operatorKey, roleKey);
	}

	@Override
	public void deleteLinkUserToRole(String operatorKey, String roleKey)
			throws ModelException{
		getEJBUserRolesServiceFacade().deleteLinkUserToRole(operatorKey, roleKey);	
	}

	@Override
	public void setStatusLinkUserToRole(String operatorKey, String roleKey,
			String status) throws ModelException {
		getEJBUserRolesServiceFacade().setStatusLinkUserToRole(operatorKey, roleKey, status);		
	}

	@Override
	public ArrayList<?> findUserToRoleAccessList(String operatorKey,
			String processID, String orderBy) throws ModelException {
		return getEJBUserRolesServiceFacade()
				.findUserToRoleAccessList(operatorKey, processID, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleList(String operatorKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesServiceFacade().findUserToRoleList(operatorKey, orderBy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.masterdm.User.model.CompendiumActionProcessor#clearInMemoryCaches
	 * ()
	 */
	@Override
	public void clearInMemoryCaches() {
		// TODO Auto-generated method stub
	}

	@Override
	public UserTO findUserTO(String userID) throws ModelException {
		return getEJBUserServiceFacade().findUserTO(userID);
	}

	@Override
	public Page findUserPage(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws ModelException {
		return getEJBUserServiceFacade().findUserPage(searchFilter, searchStr, start, count, orderBy);
	}

	@Override
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().addLinkUserToRoleCrCo(userKey, roleCrCoKey);
	}

	@Override
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().deleteLinkUserToRoleCrCo(userKey, roleCrCoKey);
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoAccessList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesServiceFacade().findUserToRoleCrCoAccessList(userKey, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesServiceFacade().findUserToRoleCrCoList(userKey, orderBy);
	}

	@Override
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,
			String status) throws ModelException {
		getEJBUserRolesServiceFacade().setStatusLinkUserToRoleCrCo(userKey, roleCrCoKey, status);
	}

	@Override
	public void addUser(User newUser) throws ModelException {
		getEJBUserServiceFacade().insertUser(newUser);		
	}

	@Override
	public User getUser(User vo) throws ModelException {
		return getEJBUserServiceFacade().getUser(vo);
	}
	
	@Override
	public UserTO findUserByLogin(String aLogin) throws ModelException {
		return getEJBUserServiceFacade().findUserByLogin(aLogin);
	}	

	@Override
	public void removeUser(User vo) throws ModelException {
		getEJBUserServiceFacade().deleteUser(vo);
	}

	@Override
	public void updateUser(User vo) throws ModelException {
		getEJBUserServiceFacade().updateUser(vo);
	}

	@Override
	public ArrayList<?> findUserToDepartmentCrCoList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserServiceFacade().findUserToDepartmentCrCoList(userKey, orderBy);
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,
			Integer[] departmentCrCoKeys) throws ModelException {
		getEJBUserServiceFacade().setLinkUserToDepartmentCrCo(userKey, departmentCrCoKeys);
	}

	@Override
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().addLinkUserToRoleRating(userKey, roleRatingKey);
	}

	@Override
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().deleteLinkUserToRoleRating(userKey, roleRatingKey);
	}

	@Override
	public ArrayList<?> findUserToRoleRatingAccessList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesServiceFacade().findUserToRoleRatingAccessList(userKey, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleRatingList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesServiceFacade().findUserToRoleRatingList(userKey, orderBy);
	}

	@Override
	public void setStatusLinkUserToRoleRating(String userKey,
			String roleRatingKey, String status) throws ModelException {
		getEJBUserRolesServiceFacade().setStatusLinkUserToRoleRating(userKey, roleRatingKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRole(String operatorKey, String status)
			throws ModelException {
		getEJBUserRolesServiceFacade().setStatusAllLinkUserToRole(operatorKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status)
			throws ModelException {
		getEJBUserRolesServiceFacade().setStatusAllLinkUserToRoleCrCo(userKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRoleRating(String userKey, String status)
			throws ModelException {
		getEJBUserRolesServiceFacade().setStatusAllLinkUserToRoleRating(userKey, status);
	}

	@Override
	public void addAllLinkUserToRoleByProcessSPO(String operatorKey,
			String processId) throws ModelException {
		getEJBUserRolesServiceFacade().addAllLinkUserToRoleByProcessSPO(operatorKey, processId);
	}

	@Override
	public void addAllLinkUserToRoleCrCo(String operatorKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().addAllLinkUserToRoleCrCo(operatorKey);
	}

	@Override
	public void addAllLinkUserToRoleRating(String operatorKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().addAllLinkUserToRoleRating(operatorKey);
	}

	@Override
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey,
			String processId) throws ModelException {
		getEJBUserRolesServiceFacade().deleteAllLinkUserToRoleByProcessSPO(operatorKey, processId);
	}

	@Override
	public void deleteAllLinkUserToRoleCrCo(String operatorKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().deleteAllLinkUserToRoleCrCo(operatorKey);
	}

	@Override
	public void deleteAllLinkUserToRoleRating(String operatorKey)
			throws ModelException {
		getEJBUserRolesServiceFacade().deleteAllLinkUserToRoleRating(operatorKey);
	}

	@Override
	public QuestionType[] findQuestionTypeList(String name, String orderBy)
			throws ModelException {
		return getEJBCompendiumServiceFacade().getQuestionTypeLike(name, orderBy);
	}

	@Override
	public void removeQuestionType(QuestionType vo) throws ModelException {
		getEJBCompendiumServiceFacade().deleteQuestionType(vo);		
	}

	@Override
	public QuestionType findQuestionType(QuestionType vo) throws ModelException {
		return getEJBCompendiumServiceFacade().getQuestionType(vo);
	}

	@Override
	public void addQuestionType(QuestionType vo) throws ModelException {
		getEJBCompendiumServiceFacade().insertQuestionType(vo);		
	}

	@Override
	public void updateQuestionType(QuestionType vo) throws ModelException {
		getEJBCompendiumServiceFacade().updateQuestionType(vo);
	}

	@Override
	public void addDepartment(DepartmentTO to) throws ModelException {
		getEJBCompendiumServiceFacade().insertDepartment(to);
	}

	@Override
	public Department getDepartment(Department vo) throws ModelException {
		return getEJBCompendiumServiceFacade().getDepartment(vo);
	}

	@Override
	public Department[] getDepartmentListAll() throws ModelException {
		return getEJBCompendiumServiceFacade().getDepartmentListAll();
	}

	@Override
	public DepartmentPar[] getDepartmentParAll() throws ModelException {
		return getEJBCompendiumServiceFacade().getDepartmentParAll();
	}

	@Override
	public void removeDepartment(Department vo) throws ModelException {
		getEJBCompendiumServiceFacade().deleteDepartment(vo);
	}

	@Override
	public void updateDepartment(DepartmentTO to) throws ModelException {
		getEJBCompendiumServiceFacade().updateDepartment(to);
	}	
}
