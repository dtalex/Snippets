package org.rao.user.jpa_gui.actions;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.QueryHints;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.Test;
import org.rao.user.jpa_gui.db.DBProvider;
import org.rao.user.jpa_gui.models.RemapField;
import org.rao.user.jpa_gui.models.pks.RemapFieldPk;

import net.sf.ehcache.CacheManager;

public class ActionTest  extends JPAHibernateTest{
	
	@Test
	public void cacheinTransaction() throws Exception {
		
		em = DBProvider.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RemapField> cq = cb.createQuery(RemapField.class);
		Root<RemapField> rootEntry = cq.from(RemapField.class);
		CriteriaQuery<RemapField> all = cq.select(rootEntry);
	    TypedQuery<RemapField> allQuery = em.createQuery(all);
	    List<RemapField> results = allQuery.getResultList();
	    RemapField primo = results.get(0);
	    System.out.println(results.size());
	
		
		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(RemapField.class);
		rootEntry = cq.from(RemapField.class);
		all = cq.select(rootEntry);
	    allQuery = em.createQuery(all);
	    results = allQuery.getResultList();
	    RemapField secondo = results.get(0);
	    
		boolean resultCompare = primo == secondo;
		assertTrue(resultCompare);


	}
	
	@Test
	public void cacheBetweenTransactionFind() throws Exception {

		em = DBProvider.getEntityManager();
		
	    em.find(RemapField.class, new RemapFieldPk("cazzoId","cazzo"));

		DBProvider.commit();
		DBProvider.closeEntityManager();
		em= DBProvider.getEntityManager();
		DBProvider.beginTransaction();
		
		em.find(RemapField.class, new RemapFieldPk("cazzoId","cazzo"));
		EntityManagerFactory emf = em.getEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)emf;

		System.out.println((empImpl.getSessionFactory().getStatistics()));
		assertEquals (empImpl.getSessionFactory().getStatistics().getSecondLevelCacheHitCount(),1l);


	}
	@Test
	public void emptyCacheFind() throws Exception {

		em = DBProvider.getEntityManager();
		
	    em.find(RemapField.class, new RemapFieldPk("cazzoId","cazzo"));

		DBProvider.commit();
		em.getEntityManagerFactory().getCache().evictAll();
		DBProvider.closeEntityManager();
		
		em= DBProvider.getEntityManager();
		DBProvider.beginTransaction();
		
		em.find(RemapField.class, new RemapFieldPk("cazzoId","cazzo"));
		EntityManagerFactory emf = em.getEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)emf;

		System.out.println((empImpl.getSessionFactory().getStatistics()));
		assertEquals (empImpl.getSessionFactory().getStatistics().getSecondLevelCacheHitCount(),0l);
	}
	
	
	@Test
	public void cacheBetweenTransactionCriteria() throws Exception {

		em = DBProvider.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RemapField> cq = cb.createQuery(RemapField.class);
		Root<RemapField> rootEntry = cq.from(RemapField.class);
		CriteriaQuery<RemapField> all = cq.select(rootEntry);
		cq.where(cb.equal(rootEntry.get("remapId"), "cazzoId"));
		cq.where(cb.equal(rootEntry.get("valueToRemap"), "cazzo"));
	    TypedQuery<RemapField> allQuery = em.createQuery(all);
	    allQuery.setHint("org.hibernate.cacheable", true);
	    allQuery.getResultList();
		
		DBProvider.commit();
		DBProvider.closeEntityManager();
		em= DBProvider.getEntityManager();
		DBProvider.beginTransaction();
		
		cb = em.getCriteriaBuilder();
		cq = cb.createQuery(RemapField.class);
		cq.where(cb.equal(rootEntry.get("remapId"), "cazzoId"));
		cq.where(cb.equal(rootEntry.get("valueToRemap"), "cazzo"));
		rootEntry = cq.from(RemapField.class);
		all = cq.select(rootEntry);
	    allQuery = em.createQuery(all);
	    allQuery.setHint("org.hibernate.cacheable", true);
	    allQuery.getResultList();
	
		EntityManagerFactory emf = em.getEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)emf;
		assertEquals (empImpl.getSessionFactory().getStatistics().getSecondLevelCacheHitCount(),1l);
	}
	
	@Test
	public void cacheBetweenTransactionQuery() throws Exception {

		em = DBProvider.getEntityManager();
		String query="from RemapField where remapId = :a";
		Query q = em.createQuery(query);
		q.setParameter("a", "cazzoId");
		q.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		q.getResultList();
			   
		DBProvider.commit();
		DBProvider.closeEntityManager();
		em= DBProvider.getEntityManager();

		DBProvider.beginTransaction();
		q = em.createQuery(query);
		q.setParameter("a", "cazzoId");
		q.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		q.getResultList();

		EntityManagerFactory emf = em.getEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)emf;
//		System.out.println(empImpl.getSessionFactory().getStatistics());
//		System.out.println(CacheManager.getInstance().getCache("org.rao.user.jpa_gui.models.RemapField"));
		assertEquals (empImpl.getSessionFactory().getStatistics().getSecondLevelCacheHitCount(),1l);


	}
}
