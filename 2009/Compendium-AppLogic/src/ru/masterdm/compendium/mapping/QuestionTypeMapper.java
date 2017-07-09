package ru.masterdm.compendium.mapping;

import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.MappingException;

/**
 * QuestionType Mapper
 * @author IShafigullin
 *
 */
public interface QuestionTypeMapper extends Mapper {
	public QuestionType[] getQuestionTypeLike(String likeName, String orderBy) throws MappingException;
}
