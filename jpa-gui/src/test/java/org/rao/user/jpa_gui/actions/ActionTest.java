package org.rao.user.jpa_gui.actions;

import com.opensymphony.xwork2.ActionSupport;

import static org.junit.Assert.*;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.rao.user.jpa_gui.db.DBProvider;

public class ActionTest  extends JPAHibernateTest{
	
	@Test
	public void testIndexAction() throws Exception {
		Index hello = new Index();
		System.out.println("iniziato");
		
		
		String result = hello.execute();
		assertTrue("Expected a success result!",
				ActionSupport.SUCCESS.equals(result));
		
		System.out.println("asserted");

	}
}
