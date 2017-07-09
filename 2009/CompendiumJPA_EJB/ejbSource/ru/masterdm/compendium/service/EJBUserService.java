package ru.masterdm.compendium.service;

import java.util.ArrayList;

import javax.ejb.Local;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.value.Page;

/**
 * Application Model interface for VTB local manipulation services for Users.
 * @author IShafigullin
 *
 */
@Local
public interface EJBUserService {
	
	@SuppressWarnings("unchecked")
	public ArrayList findProcessTypeList(String nameProcessType, String orderBy) throws ModelException;

	public Page findUserPage(int searchFilter, String searchStr, int start, int count,	String orderBy) throws ModelException;
	
	public UserTO findUserTO(String userID) throws ModelException;
	public UserTO findUserByLogin(String aLogin) throws ModelException;
	
	public void insertUser(User vo) throws ModelException;
	public User getUser(User vo) throws ModelException;
	public void deleteUser(User vo) throws ModelException;
	public void updateUser(User vo) throws ModelException;

	@SuppressWarnings("unchecked")
	public ArrayList findUserToDepartmentCrCoList(String userKey, String orderBy) throws ModelException;
	public void setLinkUserToDepartmentCrCo(String userKey,	Integer[] departmentCrCoKeys) throws ModelException;
}
