/*
 * Created on 13.03.2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.model.test;

import junit.framework.TestCase;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.util.ApplProperties;

/**
 * @author IShaffigulin
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CompendiumActionProcessorTests extends TestCase {
	/**
	 * Constructor for CrmActionProcessorTests.
	 * 
	 * @param arg0
	 */
	public CompendiumActionProcessorTests(String arg0) {
		super(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		if (ApplProperties.getCurrentMapperName().equals("EJB")) {
			// deleteAllRows();
		}
		if (ApplProperties.getCurrentMapperName().equals("MEMORY")) {
			deleteAllCache();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		if (ApplProperties.getCurrentMapperName().equals("EJB")) {
			// deleteAllRows();
		}
		if (ApplProperties.getCurrentMapperName().equals("MEMORY")) {
			deleteAllCache();
		}
	}

	/**
	 * Method deleteAllCache.
	 */
	private void deleteAllCache() {
	}

	/**
	 * test for "QuestionType"
	 * 
	 */
	public void testAddFindDeleteQuestionType() {
	
		// test adding, finding and removing a QuestionType from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			Integer key = new Integer("-1");
			String name = new String("QuestionTypeName");
	
			QuestionType purple = new QuestionType(key, name);
	
			processor.addQuestionType(purple);
	
			QuestionType clone = processor.findQuestionType(purple);
			assertEquals("Name don't Match for QuestionType", purple.getName(), clone.getName());
	
			processor.removeQuestionType(purple);
	
			try {
				processor.findQuestionType(purple);
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}
	
	/**
	 * test for "Department"
	 * 
	 */
	public void testAddFindDeleteDepartment() {
	
		// test adding, finding and removing a Department from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			Integer key = new Integer("-1");
			String shortName, fullName;
			shortName = fullName = new String("DepartmentName");
	
			Department vo = new Department(key, shortName, fullName, new java.math.BigDecimal(-1));
			Integer ROOT_KEY = -1;//нет родителя
			DepartmentTO purple = new DepartmentTO(ROOT_KEY, vo);
	
			processor.addDepartment(purple);
	
			Department clone = processor.getDepartment(purple.getVo());
			assertEquals("ShortName don't Match for Department", purple.getVo().getShortName(), clone.getShortName());
			assertEquals("FullName don't Match for Department", purple.getVo().getShortName(), clone.getFullName());
	
			processor.removeDepartment(purple.getVo());
	
			try {
				processor.getDepartment(purple.getVo());
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}	
}
