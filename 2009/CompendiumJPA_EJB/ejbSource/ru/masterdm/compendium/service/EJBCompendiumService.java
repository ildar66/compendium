package ru.masterdm.compendium.service;

import javax.ejb.Local;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.ModelException;

/**
 * Application Model interface for VTB local manipulation services.
 * @author IShafigullin
 *
 */
@Local
public interface EJBCompendiumService {
	//for QuestionType:
	public QuestionType getQuestionType(QuestionType vo) throws ModelException;
	
	public void deleteQuestionType(QuestionType vo) throws ModelException;
	
	public void updateQuestionType(QuestionType vo) throws ModelException;
	
	public void insertQuestionType(QuestionType vo) throws ModelException;
	
	public QuestionType[] getQuestionTypeLike(String name, String orderBy) throws ModelException;	
	
	//for Department:
	public Department[] getDepartmentListAll() throws ModelException;

	public DepartmentPar[] getDepartmentParAll() throws ModelException;
	
	public Department getDepartment(Department vo) throws ModelException;
	
	public void deleteDepartment(Department vo) throws ModelException;
	
	public void updateDepartment(DepartmentTO to) throws ModelException;
	
	public void insertDepartment(DepartmentTO to) throws ModelException;
}
