package ru.masterdm.compendium.mapping;

import java.util.ArrayList;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.value.Page;

/**
 * Mapper "пользователи системы"
 * @author IShafigullin
 *
 */
public interface UserMapper extends Mapper {
	public UserTO findUserTO(String userID) throws MappingException;

	public Page findPageByFilter(int searchFilter, String searchStr, int start,
			int count, String orderBy) throws MappingException;
	
	public UserTO findUserByLogin(String aLogin) throws MappingException;
	
	public ArrayList<UserToDepartmentTO> findUserToDepartmentCrCoList(String userKey, String orderBy) throws MappingException;
	
	public void setLinkUserToDepartmentCrCo(String userKey, Integer[] departmentCrCoKeys) throws MappingException;	
}