package org.rao.user.jpa_gui.actions;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rao.user.jpa_gui.db.DBProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class JPAHibernateTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {

    }

    @Before
    public void initializeDatabase(){
	System.setProperty("alt-ds", "primary-test");
	DBProvider.getInstance();
	DBProvider.beginTransaction();
    }
    
    @After
    public void quit(){
    	DBProvider.rollback();
    	DBProvider.closeEntityManager();
    	DBProvider.destroy();
    }

    @AfterClass
    public static void tearDown(){
//		DBProvider.commit();
//		DBProvider.closeEntityManager();
//		DBProvider.destroy();
    }
}