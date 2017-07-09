/**
 * 
 */
package ru.masterdm.compendium.model.ejb;

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
import ru.masterdm.compendium.service.EJBCompendiumRemote;
import ru.masterdm.compendium.service.EJBUserRemote;
import ru.masterdm.compendium.service.EJBUserRolesRemote;
import ru.masterdm.compendium.util.EjbLocator;
import ru.masterdm.compendium.value.Page;

/**
 * Compendium ActionProcessor Implementation.
 * Application Model interface for VTB remote manipulation services.
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
	 * Access (create) an instance of the DictionaryActionProcessorFacade
	 * @throws ModelException 
	 * 
	 * @returns a new EJBUserRolesRemote session facade bean
	 */
	protected EJBUserRolesRemote getEJBUserRolesRemoteFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBUserRolesRemote.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBUserRolesRemote not found");
		}
	}	
	
	/**
	 * Access (create) an instance of the DictionaryActionProcessorFacade
	 * @throws ModelException 
	 * 
	 * @returns a new EJBUserRemote session facade bean
	 */
	protected EJBUserRemote getEJBUserRemoteFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBUserRemote.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBUserRemote not found");
		}
	}
	
	/**
	 * Access (create) an instance of the DictionaryActionProcessorFacade
	 * @throws ModelException 
	 * 
	 * @returns a EJBCompendiumRemote session facade bean
	 */
	protected EJBCompendiumRemote getEJBCompendiumRemoteFacade() throws ModelException {
		try {
			return EjbLocator.getInstance().getReference(
					EJBCompendiumRemote.class);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception caught " + e);
			throw new ModelException(e, "EJBCompendiumRemote not found");
		}
	}	

	@Override
	public ArrayList<?> findProcessTypeList(String nameProcessType, String orderBy)
			throws ModelException {
		return getEJBUserRemoteFacade().findProcessTypeList(nameProcessType, orderBy);
	}

	@Override
	public void addLinkUserToRole(String operatorKey, String roleKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().addLinkUserToRole(operatorKey, roleKey);
	}

	@Override
	public void deleteLinkUserToRole(String operatorKey, String roleKey)
			throws ModelException{
		getEJBUserRolesRemoteFacade().deleteLinkUserToRole(operatorKey, roleKey);	
	}

	@Override
	public void setStatusLinkUserToRole(String operatorKey, String roleKey,
			String status) throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusLinkUserToRole(operatorKey, roleKey, status);		
	}

	@Override
	public ArrayList<?> findUserToRoleAccessList(String operatorKey,
			String processID, String orderBy) throws ModelException {
		return getEJBUserRolesRemoteFacade()
				.findUserToRoleAccessList(operatorKey, processID, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleList(String operatorKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesRemoteFacade().findUserToRoleList(operatorKey, orderBy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.masterdm.compendium.model.CompendiumActionProcessor#clearInMemoryCaches
	 * ()
	 */
	@Override
	public void clearInMemoryCaches() {
		// TODO Auto-generated method stub
	}

	@Override
	public UserTO findUserTO(String userID) throws ModelException {
		return getEJBUserRemoteFacade().findUserTO(userID);
	}

	@Override
	public Page findUserPage(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws ModelException {
		return getEJBUserRemoteFacade().findUserPage(searchFilter, searchStr, start, count, orderBy);
	}

	@Override
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().addLinkUserToRoleCrCo(userKey, roleCrCoKey);
	}

	@Override
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().deleteLinkUserToRoleCrCo(userKey, roleCrCoKey);
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoAccessList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesRemoteFacade().findUserToRoleCrCoAccessList(userKey, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleCrCoList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesRemoteFacade().findUserToRoleCrCoList(userKey, orderBy);
	}

	@Override
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,
			String status) throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusLinkUserToRoleCrCo(userKey, roleCrCoKey, status);
	}

	@Override
	public void addUser(User newUser) throws ModelException {
		getEJBUserRemoteFacade().insertUser(newUser);		
	}

	@Override
	public User getUser(User vo) throws ModelException {
		return getEJBUserRemoteFacade().getUser(vo);
	}
	
	@Override
	public UserTO findUserByLogin(String aLogin) throws ModelException {
		return getEJBUserRemoteFacade().findUserByLogin(aLogin);
	}	

	@Override
	public void removeUser(User vo) throws ModelException {
		getEJBUserRemoteFacade().deleteUser(vo);
	}

	@Override
	public void updateUser(User vo) throws ModelException {
		getEJBUserRemoteFacade().updateUser(vo);
	}

	@Override
	public ArrayList<?> findUserToDepartmentCrCoList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRemoteFacade().findUserToDepartmentCrCoList(userKey, orderBy);
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,
			Integer[] departmentCrCoKeys) throws ModelException {
		getEJBUserRemoteFacade().setLinkUserToDepartmentCrCo(userKey, departmentCrCoKeys);
	}

	@Override
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().addLinkUserToRoleRating(userKey, roleRatingKey);
	}

	@Override
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().deleteLinkUserToRoleRating(userKey, roleRatingKey);
	}

	@Override
	public ArrayList<?> findUserToRoleRatingAccessList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesRemoteFacade().findUserToRoleRatingAccessList(userKey, orderBy);
	}

	@Override
	public ArrayList<?> findUserToRoleRatingList(String userKey, String orderBy)
			throws ModelException {
		return getEJBUserRolesRemoteFacade().findUserToRoleRatingList(userKey, orderBy);
	}

	@Override
	public void setStatusLinkUserToRoleRating(String userKey,
			String roleRatingKey, String status) throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusLinkUserToRoleRating(userKey, roleRatingKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRole(String operatorKey, String status)
			throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusAllLinkUserToRole(operatorKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status)
			throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusAllLinkUserToRoleCrCo(userKey, status);
	}

	@Override
	public void setStatusAllLinkUserToRoleRating(String userKey, String status)
			throws ModelException {
		getEJBUserRolesRemoteFacade().setStatusAllLinkUserToRoleRating(userKey, status);
	}

	@Override
	public void addAllLinkUserToRoleByProcessSPO(String operatorKey,
			String processId) throws ModelException {
	}

	@Override
	public void addAllLinkUserToRoleCrCo(String operatorKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().addAllLinkUserToRoleCrCo(operatorKey);
	}

	@Override
	public void addAllLinkUserToRoleRating(String operatorKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().addAllLinkUserToRoleRating(operatorKey);
	}

	@Override
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey,
			String processId) throws ModelException {
		getEJBUserRolesRemoteFacade().deleteAllLinkUserToRoleByProcessSPO(operatorKey, processId);
	}

	@Override
	public void deleteAllLinkUserToRoleCrCo(String operatorKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().deleteAllLinkUserToRoleCrCo(operatorKey);
	}

	@Override
	public void deleteAllLinkUserToRoleRating(String operatorKey)
			throws ModelException {
		getEJBUserRolesRemoteFacade().deleteAllLinkUserToRoleRating(operatorKey);
	}

	@Override
	public QuestionType[] findQuestionTypeList(String name, String orderBy)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestionType(QuestionType vo) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QuestionType findQuestionType(QuestionType vo) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeQuestionType(QuestionType vo) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuestionType(QuestionType vo) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDepartment(DepartmentTO to) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department getDepartment(Department vo) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department[] getDepartmentListAll() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentPar[] getDepartmentParAll() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDepartment(Department vo) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDepartment(DepartmentTO to) throws ModelException {
		// TODO Auto-generated method stub
		
	}
}
