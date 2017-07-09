package ru.masterdm.compendium.mapping;

import java.util.ArrayList;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.exception.MappingException;

/**
 * Mapper "филиалов"
 * @author IShafigullin
 *
 */
public interface DepartmentMapper extends Mapper {

	public ArrayList<?> findByName(String name, String orderBy) throws MappingException;

	void deleteLinkUserToDepartmentCrCo(String departmentKey, String roleCrCoKey) throws MappingException;

	void addLinkUserToDepartmentCrCo(String departmentKey, String roleCrCoKey) throws MappingException;

	ArrayList<?> findUserToDepartmentCrCoList(String userKey, String orderBy) throws MappingException;
	
	void setLinkUserToDepartmentCrCo(String userKey, Integer[] departmentCrCoKeys) throws MappingException;	
	
	DepartmentPar[] getDepartmentParAll() throws MappingException;
}
