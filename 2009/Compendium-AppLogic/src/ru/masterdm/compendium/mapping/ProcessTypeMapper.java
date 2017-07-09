package ru.masterdm.compendium.mapping;

import java.util.ArrayList;

import ru.masterdm.compendium.exception.MappingException;

/**
 * Mapper "тип процесса"
 * @author IShafigullin
 *
 */
public interface ProcessTypeMapper extends Mapper {
	@SuppressWarnings("unchecked")
	public ArrayList findByName(String name, String orderBy) throws MappingException;
}
