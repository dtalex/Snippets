package org.rao.user.jpa_gui.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBProvider {
	private static DBProvider instance;
	private EntityManagerFactory factory;

	private DBProvider() {
		this.factory = Persistence.createEntityManagerFactory("primary");
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

	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();

		if (em == null) {
			em= instance.factory.createEntityManager();
			// set your flush mode here
			threadLocal.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null) {
			em.close();
			threadLocal.set(null);
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
