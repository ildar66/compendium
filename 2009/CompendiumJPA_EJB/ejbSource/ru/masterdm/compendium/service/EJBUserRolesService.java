package ru.masterdm.compendium.service;

import java.util.ArrayList;

import javax.ejb.Local;

import ru.masterdm.compendium.exception.ModelException;

/**
 * Application Model interface for VTB local manipulation services for User roles.
 * @author IShafigullin
 *
 */
@Local
public interface EJBUserRolesService {
	
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleAccessList(String operatorKey, String processID, String orderBy) throws ModelException;
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleList(String operatorKey, String orderBy)	throws ModelException;	
	public void addLinkUserToRole(String operatorKey, String roleKey) throws ModelException;
	public void deleteLinkUserToRole(String operatorKey, String roleKey) throws ModelException;
	public void setStatusLinkUserToRole(String operatorKey, String roleKey,	String status) throws ModelException;

	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleCrCoAccessList(String userKey, String orderBy) throws ModelException;
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleCrCoList(String userKey, String orderBy) throws ModelException;
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException;
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws ModelException;	
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey,	String status) throws ModelException;

	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleRatingAccessList(String userKey,	String orderBy) throws ModelException;
	@SuppressWarnings("unchecked")
	public ArrayList findUserToRoleRatingList(String userKey, String orderBy) throws ModelException;
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException;
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws ModelException;	
	public void setStatusLinkUserToRoleRating(String userKey, String roleRatingKey, String status) throws ModelException;
	
	public void setStatusAllLinkUserToRole(String operatorKey, String status) throws ModelException;
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status) throws ModelException;
	public void setStatusAllLinkUserToRoleRating(String userKey, String status) throws ModelException;

	public void addAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws ModelException;
	public void addAllLinkUserToRoleCrCo(String operatorKey) throws ModelException;
	public void addAllLinkUserToRoleRating(String operatorKey) throws ModelException;
	
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws ModelException;
	public void deleteAllLinkUserToRoleCrCo(String operatorKey) throws ModelException;
	public void deleteAllLinkUserToRoleRating(String operatorKey) throws ModelException;	
}
