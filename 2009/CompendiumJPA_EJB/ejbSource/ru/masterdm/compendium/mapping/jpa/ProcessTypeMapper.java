/**
 * 
 */
package ru.masterdm.compendium.mapping.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import ru.masterdm.compendium.domain.ProcessType;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.entities.ProcessTypeJPA;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;

/**
 * Mapper "тип процесса" implementation.
 * @author IShafigullin
 *
 */
public class ProcessTypeMapper extends DomainJPAMapper implements ru.masterdm.compendium.mapping.ProcessTypeMapper{

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#findJPAObjectMatching(ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	public Object findJPAObjectMatching(VtbObject domainObject)
			throws MappingException {
		ru.masterdm.compendium.domain.ProcessType vtbObject = (ru.masterdm.compendium.domain.ProcessType)domainObject;
		return getEntityMgr().find(ProcessTypeJPA.class, new Long(vtbObject.getId()));
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#map(java.lang.Object)
	 */
	@Override
	public VtbObject map(Object input) throws MappingException {
		Long jpaKey = ((ProcessTypeJPA)input).getIdTypeProcess();
		Integer id = jpaKey.intValue();
		String name = ((ProcessTypeJPA)input).getDescriptionProcess();
		return new ProcessType(id, name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProcessType> findByName(String likeName, String orderBy)
			throws MappingException {
		Query query = null;
		String sqlStr = "SELECT p FROM ProcessTypeJPA p WHERE p.descriptionProcess LIKE :descriptionProcess ";
		if (orderBy != null && !"".equals(orderBy)) {
			sqlStr += " ORDER BY " + orderBy;
		}
		try {
			query = getEntityMgr().createQuery(sqlStr);
			query.setParameter(1, likeName);
			List<ProcessTypeJPA> listJPA = (List<ProcessTypeJPA>)query.getResultList();
			ArrayList<ProcessType> list = new ArrayList<ProcessType>();
			for (Iterator<ProcessTypeJPA> iterator = listJPA.iterator(); iterator.hasNext();) {
				list.add((ProcessType)map(iterator.next())); 
			}
			return list;
		} catch (Exception e) {
			throw new MappingException(e, ("Exception caught in getProcessTypeLike " + e));
		}
	}

	@Override
	public void insert(VtbObject anObject) throws DuplicateKeyException,
			MappingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VtbObject anObject) throws NoSuchObjectException,
			MappingException {
		// TODO Auto-generated method stub
		
	}

}
