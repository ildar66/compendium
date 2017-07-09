package ru.masterdm.compendium.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.MappingException;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.exception.NoSuchObjectException;
import ru.masterdm.compendium.mapping.DepartmentMapper;
import ru.masterdm.compendium.mapping.Mapper;
import ru.masterdm.compendium.mapping.MapperFactory;
import ru.masterdm.compendium.mapping.QuestionTypeMapper;
import ru.masterdm.compendium.service.EJBCompendiumRemote;
import ru.masterdm.compendium.service.EJBCompendiumService;

/**
 * Application Model implementations for VTB general manipulation services.
 * Session Bean implementation class EJBCompendiumBean
 */
@Stateless
@PersistenceContext(name="CompendiumJPA", unitName = "CompendiumJPA", type = PersistenceContextType.TRANSACTION)
public class EJBCompendiumBean implements EJBCompendiumService,
		EJBCompendiumRemote {
	
	@Override
	public QuestionType getQuestionType(QuestionType domainObjectWithKeyValues) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(QuestionType.class);
			return (QuestionType)mapper.findByPrimaryKey(domainObjectWithKeyValues);
		} catch (Exception e) {
			throw new ModelException(e,
					("MappingException caught in getQuestionType " + e));
		}
	}

	@Override
	public void deleteQuestionType(QuestionType anObject) throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(QuestionType.class);
			mapper.remove(anObject);
		} catch (Exception e) {
			throw new ModelException(e,	("MappingException caught in deleteQuestionType " + e));
		}
	}

	@Override
	public void insertQuestionType(QuestionType anObject) throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(QuestionType.class);
			mapper.insert(anObject);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException caught in insertQuestionType " + e);
		}
	}

	@Override
	public void updateQuestionType(QuestionType anObject) throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(QuestionType.class);
			mapper.update(anObject);
		} catch (Exception e) {
			throw new ModelException(e,	"MappingException caught in updateQuestionType " + e);
		}
	}	

	@Override
	public QuestionType[] getQuestionTypeLike(String likeName, String orderBy) throws ModelException {
		try {
			QuestionTypeMapper mapper = (QuestionTypeMapper) MapperFactory.getReserveMapperFactory().getMapper(QuestionType.class);
			return mapper.getQuestionTypeLike(likeName, orderBy);
		} catch (Exception e) {
			throw new ModelException(e,	"MappingException caught in getQuestionTypeLike " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Department[] getDepartmentListAll() throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(Department.class);
			ArrayList list = mapper.findAll();
			Department[] array = new Department[list.size()];		
			return (Department[]) list.toArray(array);			
		} catch (MappingException e) {
			throw new ModelException(e,	"MappingException caught in getDepartmentListAll " + e);
		}
	}

	@Override
	public DepartmentPar[] getDepartmentParAll() throws ModelException {
		try {
			DepartmentMapper mapper = (DepartmentMapper) MapperFactory.getReserveMapperFactory().getMapper(Department.class);
			return (DepartmentPar[]) mapper.getDepartmentParAll();			
		} catch (MappingException e) {
			throw new ModelException(e,	"MappingException caught in getDepartmentParAll " + e);
		}
	}

	@Override
	public void deleteDepartment(Department vo) throws ModelException {
		Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(Department.class);
		try {
			mapper.remove(vo);
		} catch (NoSuchObjectException e) {
			throw new ModelException(e,	"NoSuchObjectException caught in deleteDepartment " + e);
		} catch (MappingException e) {
			throw new ModelException(e,	"MappingException caught in deleteDepartment " + e);
		}		
	}

	@Override
	public Department getDepartment(Department vo) throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(Department.class);
			return (Department)mapper.findByPrimaryKey(vo);
		} catch (MappingException e) {
			throw new ModelException(e, "Exception=" + e);
		}
	}

	@Override
	public void insertDepartment(DepartmentTO to) throws ModelException {
		try {
			Mapper mapper =  MapperFactory.getReserveMapperFactory().getMapper(Department.class);
			mapper.insert(to);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException=" + e);
		}		
	}

	@Override
	public void updateDepartment(DepartmentTO to) throws ModelException {
		try {
			Mapper mapper = MapperFactory.getReserveMapperFactory().getMapper(Department.class);
			mapper.update(to);
		} catch (MappingException e) {
			throw new ModelException(e, "MappingException=" + e);
		}
	}
}
