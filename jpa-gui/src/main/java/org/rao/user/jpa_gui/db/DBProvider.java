package org.rao.user.jpa_gui.db;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBProvider {
	private static DBProvider instance;
	private EntityManagerFactory factory;
	private static final ThreadLocal<EntityManager> threadLocalEm = new ThreadLocal<>();
	
	
	private DBProvider() {
		this.factory = Persistence.createEntityManagerFactory(System.getProperty("alt-ds","primary"));
	}

	public static DBProvider getInstance() {
		if (instance == null) {
			synchronized (DBProvider.class) {
				if (instance == null) {
					instance = new DBProvider();
				}
			}
		}
		
		return instance;
	}

	public static void destroy() {
		synchronized (DBProvider.class) {
			instance.factory.close();
			instance = null;
		}
	}

	

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocalEm.get();

		if (em == null) {
			em= instance.factory.createEntityManager();
			em.setProperty(
				      "javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
			em.setProperty(
				      "javax.persistence.cache.storeMode", CacheStoreMode.USE);
			threadLocalEm.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocalEm.get();
		if (em != null) {
			em.close();
			threadLocalEm.set(null);
		}
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}
	public boolean isTransactionActive(){
		return getEntityManager().getTransaction().isActive();
	}

}
