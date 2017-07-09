/*
 * Created on 30.09.2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ru.masterdm.model.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author IShaffigulin
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for Compendium");
		// $JUnit-BEGIN$
		suite.addTest(new TestSuite(CompendiumActionProcessorTests.class));
		suite.addTest(new TestSuite(UserActionProcessorTests.class));
		suite.addTest(new TestSuite(UserRolesActionProcessorTests.class));
		// $JUnit-END$
		return suite;
	}
}
