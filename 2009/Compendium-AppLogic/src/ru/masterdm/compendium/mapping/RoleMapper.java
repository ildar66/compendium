/*
 * Created on 24.07.2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.compendium.mapping;

import java.util.ArrayList;

import ru.masterdm.compendium.exception.MappingException;
/**
 * Mapper "роли пользователей системы"
 * @author IShafigullin
 *
 */
public interface RoleMapper extends Mapper {
	//для СПО:
	public ArrayList<?> findUserToRoleList(String operatorKey, String orderBy) throws MappingException;
	public ArrayList<?> findUserToRoleAccessList(String operatorKey, String aProcessID, String orderBy) throws MappingException;
	public void deleteLinkUserToRole(String operatorKey, String roleKey) throws MappingException;
	public void addLinkUserToRole(String operatorKey, String roleKey) throws MappingException;
	public void setStatusLinkUserToRole(String operatorKey, String roleKey, String status) throws MappingException;
	public void setStatusAllLinkUserToRole(String operatorKey, String status) throws MappingException;
	
	//для кредитных комитетов:
	public ArrayList<?> findUserToRoleCrCoAccessList(String userKey, String orderBy) throws MappingException;
	public ArrayList<?> findUserToRoleCrCoList(String userKey, String orderBy) throws MappingException;
	public void setStatusLinkUserToRoleCrCo(String userKey, String roleCrCoKey, String status) throws MappingException;
	public void setStatusAllLinkUserToRoleCrCo(String userKey, String status) throws MappingException;
	public void deleteLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws MappingException;
	public void addLinkUserToRoleCrCo(String userKey, String roleCrCoKey) throws MappingException;
	
	//для рейтингов:
	public ArrayList<?> findUserToRoleRatingAccessList(String userKey, String orderBy) throws MappingException;
	public ArrayList<?> findUserToRoleRatingList(String userKey, String orderBy) throws MappingException;
	public void setStatusLinkUserToRoleRating(String userKey, String roleRatingKey, String status) throws MappingException;
	public void setStatusAllLinkUserToRoleRating(String userKey, String status) throws MappingException;
	public void deleteLinkUserToRoleRating(String userKey, String roleRatingKey) throws MappingException;
	public void addLinkUserToRoleRating(String userKey, String roleRatingKey) throws MappingException;	
	
	public void addAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws MappingException;
	public void addAllLinkUserToRoleCrCo(String operatorKey) throws MappingException;
	public void addAllLinkUserToRoleRating(String operatorKey) throws MappingException;
	
	public void deleteAllLinkUserToRoleByProcessSPO(String operatorKey, String processId) throws MappingException;
	public void deleteAllLinkUserToRoleCrCo(String operatorKey) throws MappingException;
	public void deleteAllLinkUserToRoleRating(String operatorKey) throws MappingException;	
}
