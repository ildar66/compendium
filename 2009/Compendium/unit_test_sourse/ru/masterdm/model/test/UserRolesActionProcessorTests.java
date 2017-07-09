/*
 * Created on 13.03.2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.model.test;

import java.util.List;

import junit.framework.TestCase;
import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToRoleTO;
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
public class UserRolesActionProcessorTests extends TestCase {
	/**
	 * Constructor for UserRolesActionProcessorTests.
	 * 
	 * @param arg0
	 */
	public UserRolesActionProcessorTests(String arg0) {
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
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteUserRoles() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			//находим доступные роли для СПО:
			List<?> list = processor.findUserToRoleAccessList(String.valueOf(userKey), "ALL", null);
			if(list.size() > 0){
				UserToRoleTO to = (UserToRoleTO)list.get(0);
				String roleKey = String.valueOf(to.getVo().getId());
				String userKeyStr = String.valueOf(userKey);
				processor.addLinkUserToRole(userKeyStr, roleKey);
				processor.setStatusLinkUserToRole(userKeyStr, roleKey, "Y");
				processor.deleteLinkUserToRole(userKeyStr, roleKey);
			}
			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}

	/**
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteCcUserRoles() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
	
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			//находим доступные роли для СПО:
			List<?> list = processor.findUserToRoleCrCoAccessList(String.valueOf(userKey), null);
			if(list.size() > 0){
				UserToRoleTO to = (UserToRoleTO)list.get(0);
				String roleCrCoKey = String.valueOf(to.getVo().getId());
				String userKeyStr = String.valueOf(userKey);
				processor.addLinkUserToRoleCrCo(userKeyStr, roleCrCoKey);
				processor.setStatusLinkUserToRoleCrCo(userKeyStr, roleCrCoKey, "Y");
				processor.deleteLinkUserToRoleCrCo(userKeyStr, roleCrCoKey);
			}
			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}

	/**
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteUserRolesRating() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			//находим доступные роли для СПО:
			List<?> list = processor.findUserToRoleRatingAccessList(String.valueOf(userKey), null);
			if(list.size() > 0){
				UserToRoleTO to = (UserToRoleTO)list.get(0);
				String roleRatingKey = String.valueOf(to.getVo().getId());
				String userKeyStr = String.valueOf(userKey);
				processor.addLinkUserToRoleRating(userKeyStr, roleRatingKey);
				processor.setStatusLinkUserToRoleRating(userKeyStr, roleRatingKey, "Y");
				processor.deleteLinkUserToRoleRating(userKeyStr, roleRatingKey);
			}
			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}

	/**
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteAllUserRoles() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			
			//находим доступные роли для СПО:
			String userKeyStr = String.valueOf(userKey);
			processor.addAllLinkUserToRoleByProcessSPO(userKeyStr, "ALL");
			processor.setStatusAllLinkUserToRole(userKeyStr, "Y");
			processor.deleteAllLinkUserToRoleByProcessSPO(userKeyStr, "ALL");

			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}

	/**
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteAllUserRolesCrCo() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			
			//находим доступные роли для СПО:
			String userKeyStr = String.valueOf(userKey);
			processor.addAllLinkUserToRoleCrCo(userKeyStr);
			String status = "Y";
			processor.setStatusAllLinkUserToRoleCrCo(userKeyStr, status);
			processor.deleteAllLinkUserToRoleCrCo(userKeyStr);
	
			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}

	/**
	 * test for "User Roles"
	 * 
	 */
	public void testAddFindDeleteAllUserRolesRating() {
	
		// test adding, finding and removing a UserRoles from the model
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
	
		try {
			String login = new String("UserLogin-3");
			//создаем тестового пользователя:
			User purple = new User(-1);
			purple.setLogin(login);
			purple.setDepartmentID(new Integer(1));
			processor.addUser(purple);
	
			UserTO clone = processor.findUserByLogin(login);
			assertEquals("Login don't match for User", purple.getLogin(), clone.getVo().getLogin());
			
			Integer userKey = clone.getVo().getId();
			
			//находим доступные роли для СПО:
			String userKeyStr = String.valueOf(userKey);
			processor.addAllLinkUserToRoleRating(userKeyStr);
			String status = "Y";
			processor.setStatusAllLinkUserToRoleRating(userKeyStr, status);
			processor.deleteAllLinkUserToRoleRating(userKeyStr);
	
			//удаляем тестового пользователя:
			processor.removeUser(new User(userKey));
	
			try {
				processor.getUser(new User(userKey));
				fail("Should have raised NoSuchObjectException");
			} catch (Exception e) {
				// Eat it -- it should fail
			}
	
		} catch (ModelException e) {
			fail("ModelException caught " + e);
		}
	}	
}
