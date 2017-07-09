/*
 * Created on 13.03.2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.model.test;

import junit.framework.TestCase;
import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.domain.User;
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
public class UserActionProcessorTests extends TestCase {
	/**
	 * Constructor for CrmActionProcessorTests.
	 * 
	 * @param arg0
	 */
	public UserActionProcessorTests(String arg0) {
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
	 * test for "User"
	 * 
	 */
	public void testAddFindDeleteUser() {
	
		// test adding, finding and removing a User from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
	
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
	
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
	
			processor.removeUser(new User(clone.getVo().getId()));
	
			try {
				processor.getUser(new User(clone.getVo().getId()));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}	
}
