package ru.masterdm.compendium.mapping.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.entities.QuestionTypeJPA;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;

/**
 * QuestionType Mapper implementation.
 * @author IShafigullin
 *
 */
public class QuestionTypeMapper extends DomainJPAMapper implements	ru.masterdm.compendium.mapping.QuestionTypeMapper {

	@Override
	protected Object findJPAObjectMatching(VtbObject domainObject) throws MappingException {
		QuestionType vo = (QuestionType)domainObject;
		return getEntityMgr().find(QuestionTypeJPA.class, (long)vo.getId());
	}

	@Override
	public VtbObject map(Object jpaObject) throws MappingException {
		QuestionTypeJPA jpa = (QuestionTypeJPA)jpaObject;
		return new QuestionType((int)jpa.getIdQuestionType(), jpa.getQuestionType());
	}

	@Override
	public void insert(VtbObject anObject) throws DuplicateKeyException, MappingException {
		QuestionType vo = (QuestionType)anObject;
		QuestionTypeJPA jpa = new QuestionTypeJPA(vo.getId(), vo.getName());
		getEntityMgr().persist(jpa);
	}

	@Override
	public void update(VtbObject anObject) throws NoSuchObjectException, MappingException {
		QuestionType vo = (QuestionType)anObject;
		QuestionTypeJPA jpa = getEntityMgr().find(QuestionTypeJPA.class, (long)vo.getId());
		jpa.setQuestionType(vo.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuestionType[] getQuestionTypeLike(String likeName, String orderBy) throws MappingException {
		Query query = null;
		String sqlStr = "SELECT c FROM QuestionTypeJPA c WHERE c.questionType LIKE :questionType ";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter(1, likeName);
			List<QuestionTypeJPA> listJPA = query.getResultList();
			ArrayList<QuestionType> list = new ArrayList<QuestionType>();
			for (Iterator<QuestionTypeJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				QuestionTypeJPA questionTypeJPA = (QuestionTypeJPA) iterator.next();
				QuestionType vo = new QuestionType((int)questionTypeJPA.getIdQuestionType(), questionTypeJPA.getQuestionType());
				list.add(vo);
			}
			QuestionType[] array = new QuestionType[list.size()];		
			return list.toArray(array);
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in getQuestionTypeLike " + e));
		}
	}

}
