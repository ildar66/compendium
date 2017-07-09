package ru.masterdm.compendium.model;

import java.io.Serializable;
import java.util.ArrayList;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.value.Page;


/**
 * Application Model interface for VTB manipulation services.
 * These include manipulation of Users, Departments, and etc.
 * @author IShafigullin
 */
public interface CompendiumActionProcessor extends Serializable {
	/**
	 * Locate all ProcessType List like by nameProcessType.
	 * 
	 * @returns a List of all ProcessType like by nameProcessType
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findProcessTypeList(String nameProcessType, String orderBy) throws ModelException;
	
	/**
	 * Delete Link User to Role.
	 */
	public void deleteLinkUserToRole(String userKey, String roleKey) throws ModelException;

	/**
	 * Add Link User to Role.
	 */
	public void addLinkUserToRole(String userKey, String roleKey) throws ModelException;
	
	/**
	 * set status Link User to Role.
	 */
	public void setStatusLinkUserToRole(String userKey, String roleKey, String status) throws ModelException;
	
	/**
	 * Delete Link User to RoleCrCo.
	 */
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException;

	/**
	 * Add Link User to RoleCrCo.
	 */
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException;
	
	/**
	 * set status Link User to RoleCrCo.
	 */
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey, String status) throws ModelException;	

	/**
	 * This is a testing method designed to allow you to clear the caches for the
	 * In-Memory Simulated Data Access Objects when neecessary.
	 */	

	/**
	 * Locate all Users by searchFilter and like searchStr.
	 * 
	 * @returns a Page of all Users by searchFilter and like searchStr
	 */
	public Page findUserPage(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws ModelException;	

	/**
	 * Locate all RoleList by userKey.
	 * @returns a List of all RoleList by userKey.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleList(String userKey, String orderBy) throws ModelException;

	/**
	 * Locate access RoleList by userKey and processID.
	 * @returns access RoleList by userKey.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleAccessList(String userKey, String aProcessID, String orderBy) throws ModelException;

	/**
	 * Locate all RoleList by userKey.
	 * @returns a List of all RoleList by userKey.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleCrCoList(String userKey, String orderBy) throws ModelException;

	/**
	 * Locate access RoleList by userKey and processID.
	 * @returns access RoleList by userKey.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleCrCoAccessList(String userKey, String orderBy) throws ModelException;
	
	/**
	 * This is a testing method designed to allow you to clear the caches for the
	 * In-Memory Simulated Data Access Objects when necessary.
	 */
	public void clearInMemoryCaches();


	/**
	 * Locate all DepartmentList by userKey.
	 * @returns a List of all DepartmentList by userKey.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList findUserToDepartmentCrCoList(String userKey, String orderBy) throws ModelException;
	
	/**
	 * set Link User to DepartmentCrCo-s.
	 */
	public void setLinkUserToDepartmentCrCo(String userKey, Integer[] departmentCrCoKeys) throws ModelException;

	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException;

	public void addLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException;

	public void setStatusLinkUserToRoleRating(String userKey,
			String roleRatingKey, String string) throws ModelException;

	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleRatingList(String id, String orderBy) throws ModelException;

	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleRatingAccessList(String id, String orderBy) throws ModelException;

	public UserTO findUserByLogin(String aLogin) throws ModelException;	
	
	public void setStatusAllLinkUserToRole(String operatorKey, String status) throws ModelException;
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status) throws ModelException;
	public void setStatusAllLinkUserToRoleRating(String userKey, String status) throws ModelException;

	public void addAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws ModelException;
	public void addAllLinkUserToRoleCrCo(String operatorKey) throws ModelException;
	public void addAllLinkUserToRoleRating(String operatorKey) throws ModelException;
	
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws ModelException;
	public void deleteAllLinkUserToRoleCrCo(String operatorKey) throws ModelException;
	public void deleteAllLinkUserToRoleRating(String operatorKey) throws ModelException;
	
	public QuestionType[] findQuestionTypeList(String name, String orderBy) throws ModelException;
	public QuestionType findQuestionType(QuestionType vo) throws ModelException;
	public void removeQuestionType(QuestionType vo) throws ModelException;
	public void updateQuestionType(QuestionType vo) throws ModelException;
	public void addQuestionType(QuestionType vo) throws ModelException;
	
	public Department[] getDepartmentListAll() throws ModelException;
	public DepartmentPar[] getDepartmentParAll() throws ModelException;
	public Department getDepartment(Department vo) throws ModelException;
	public void removeDepartment(Department vo) throws ModelException;
	public void updateDepartment(DepartmentTO to) throws ModelException;
	public void addDepartment(DepartmentTO to) throws ModelException;
	
	public UserTO findUserTO(String userID) throws ModelException;
	public User getUser(User vo) throws ModelException;
	public void removeUser(User vo) throws ModelException;
	public void updateUser(User vo) throws ModelException;
	public void addUser(User vo) throws ModelException;	
}