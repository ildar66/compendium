/**
 * 
 */
package ru.masterdm.compendium.mapping.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.VtbObject;
import ru.masterdm.compendium.entities.DepartmentJPA;
import ru.masterdm.compendium.entities.DepartmentJPAsPar;
import ru.masterdm.compendium.exception.DuplicateKeyException;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.NoSuchObjectException;

/**
 * @author IShafigullin
 *
 */
public class DepartmentMapper extends DomainJPAMapper implements ru.masterdm.compendium.mapping.DepartmentMapper{
	private static int ROOT_KEY = -1;
	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#findJPAObjectMatching(ru.masterdm.compendium.domain.VtbObject)
	 */
	@Override
	public Object findJPAObjectMatching(VtbObject domainObject)
			throws MappingException {
		Department vo = (Department)domainObject;
		return getEntityMgr().find(DepartmentJPA.class, (long)vo.getId());
	}

	/* (non-Javadoc)
	 * @see ru.masterdm.compendium.mapping.jpa.DomainJPAMapper#map(java.lang.Object)
	 */
	@Override
	public VtbObject map(Object input) throws MappingException {
		DepartmentJPA jpa = (DepartmentJPA)input;
		Department retVO = new Department((int)jpa.getIdDepartment(), jpa.getShortName(), jpa.getFullName(), jpa.getIdCommitteeType());
		if(jpa.getCbCd() != null){
			retVO.setCode(jpa.getCbCd().intValue());
		}
		if(jpa.getDepId() != null){
			retVO.setCodeDep(jpa.getDepId().intValue());
		}
		return retVO; 
	}

	public Object map(Object result, VtbObject domainObject)
			throws MappingException {
		Department vo = (Department)domainObject;
		DepartmentJPA jpa = (DepartmentJPA)result;
		jpa.setIdDepartment(vo.getId());
		jpa.setFullName(vo.getFullName());
		jpa.setShortName(vo.getShortName());
		jpa.setIdCommitteeType(vo.getCrCoType());
		if(vo.getCodeDep() != null){
			jpa.setDepId(new BigDecimal(vo.getCodeDep()));
		}
		if(vo.getCode() != null){
			jpa.setCbCd(new BigDecimal(vo.getCode()));
		}		
		return jpa;
	}

	@Override
	public void insert(VtbObject anObject) throws DuplicateKeyException, MappingException {
		DepartmentTO to = (DepartmentTO)anObject;
		DepartmentJPA jpa = (DepartmentJPA)map(new DepartmentJPA(), to.getVo());
		getEntityMgr().persist(jpa);
		if (to.getParentID() != ROOT_KEY) {
			DepartmentJPAsPar depPar = new DepartmentJPAsPar((long)to.getVo().getId());
			depPar.setIdDepartmentPar((long)to.getParentID());
			getEntityMgr().merge(depPar);
		}		
	}

	@Override
	public void update(VtbObject anObject) throws NoSuchObjectException, MappingException {
		DepartmentTO to = (DepartmentTO)anObject;
		Department vo = to.getVo();
		DepartmentJPA jpa = (DepartmentJPA)findJPAObjectMatching(vo);
		getEntityMgr().merge(map(jpa, vo));
		long keyJPA = vo.getId();
		
		long aParentID = to.getParentID();
		DepartmentJPAsPar depPar = getEntityMgr().find(DepartmentJPAsPar.class, keyJPA);
		if (aParentID != ROOT_KEY) {
			if (depPar != null) {
				depPar.setIdDepartmentPar(aParentID);
				getEntityMgr().merge(depPar);
			} else {
				depPar = new DepartmentJPAsPar(keyJPA);
				depPar.setIdDepartmentPar(aParentID);
				getEntityMgr().merge(depPar);
			}
		} else {
			if (depPar != null) {
				getEntityMgr().remove(depPar);
			}
		}		
	}
	
	

	@Override
	public void remove(VtbObject domainObject) throws NoSuchObjectException,
			MappingException {
		Department vo =(Department)domainObject;
		long keyJPA = (long)vo.getId();
		DepartmentJPAsPar depPar = getEntityMgr().find(DepartmentJPAsPar.class, keyJPA);
		if (depPar != null) {
			getEntityMgr().remove(depPar);
		}
		super.remove(domainObject);
	}

	@Override
	protected Collection<?> findAllJPAs() throws MappingException {
		Query query = getEntityMgr().createNamedQuery("getDepartmentListAll");
		return query.getResultList();
	}

	@Override
	public void addLinkUserToDepartmentCrCo(String departmentKey,
			String roleCrCoKey) throws MappingException {
		throw new MappingException("addLinkUserToDepartmentCrCo not valid for this type");		
	}

	@Override
	public void deleteLinkUserToDepartmentCrCo(String departmentKey,
			String roleCrCoKey) throws MappingException {
		throw new MappingException("deleteLinkUserToDepartmentCrCo not valid for this type");		
	}

	@Override
	public ArrayList<?> findByName(String name, String orderBy)
			throws MappingException {
		throw new MappingException("findByName not valid for this type");
	}

	@Override
	public ArrayList<?> findUserToDepartmentCrCoList(String userKey, String orderBy)
			throws MappingException {
		throw new MappingException("findUserToDepartmentCrCoList not valid for this type");
	}

	@Override
	public void setLinkUserToDepartmentCrCo(String userKey,
			Integer[] departmentCrCoKeys) throws MappingException {
		throw new MappingException("setLinkUserToDepartmentCrCo not valid for this type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public DepartmentPar[] getDepartmentParAll() throws MappingException {
		Query query = null;
		try {
			query = getEntityMgr().createNamedQuery("getDepartmentsParAll");
			List<DepartmentJPAsPar> listJPA = query.getResultList();
			ArrayList<DepartmentPar> list = new ArrayList<DepartmentPar>();
			for (Iterator<DepartmentJPAsPar> iterator = listJPA.iterator(); iterator.hasNext();) {
				DepartmentJPAsPar jpa = (DepartmentJPAsPar) iterator.next();
				DepartmentPar vo = new DepartmentPar((int)jpa.getIdDepartmentPar(), (int)jpa.getIdDepartmentChild());
				list.add(vo);
			}
			DepartmentPar[] array = new DepartmentPar[list.size()];		
			return list.toArray(array);			
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
}
