package org.rao.user.jpa_gui.actions;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.jpa.QueryHints;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;
import org.rao.user.jpa_gui.abstracts.MyAction;
import org.rao.user.jpa_gui.db.DBProvider;
import org.rao.user.jpa_gui.generic_beans.MyResult;
import org.rao.user.jpa_gui.generic_beans.MyResult.ReturnTypes;
import org.rao.user.jpa_gui.models.RemapField;
import org.rao.user.jpa_gui.models.User;
import org.rao.user.jpa_gui.utils.AnUtilClass;

import com.opensymphony.xwork2.Action;

public class Index extends MyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2780730030182709405L;

	private User currentUser;
	private List<User> allUsers;
	private RemapField remapField;
	private long cacheHits;

	
	public RemapField getRemapField() {
		return remapField;
	}

	public void setRemapField(RemapField remapField) {
		this.remapField = remapField;
	}

	public String execute(){
		return SUCCESS;
	}
	
	public String add() {
		EntityManager em = DBProvider.getEntityManager();
		em.persist(currentUser);
		result = new MyResult(ReturnTypes.OK, "success");
		return Action.SUCCESS;
	}

	public String list(){
		setAllUsers(AnUtilClass.listAllUsers());
		
//		DBProvider.getEntityManager().persist(new RemapField("cazzoId", "cazzo", "minchia"));
		
		return SUCCESS;
	}
	
	public String readMetadata() {
		EntityManager em = DBProvider.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RemapField> cq = cb.createQuery(RemapField.class);
		Root<RemapField> rootEntry = cq.from(RemapField.class);
		CriteriaQuery<RemapField> all = cq.select(rootEntry);

		cq.where(cb.equal(rootEntry.get("remapId"), remapField.getRemapId()));
		cq.where(cb.equal(rootEntry.get("valueToRemap"), remapField.getValueToRemap()));
	    TypedQuery<RemapField> allQuery = em.createQuery(all);
	    allQuery.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
	    List<RemapField> results = allQuery.getResultList();
	    remapField = results.get(0);
		return SUCCESS;
	}
	
	public String listStatistics(){
		EntityManagerFactory emf = DBProvider.getEntityManager().getEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)emf;
//		System.out.println(empImpl.getSessionFactory().getStatistics());
//		System.out.println(CacheManager.getInstance().getCache("org.rao.user.jpa_gui.models.RemapField"));
		System.out.println(empImpl.getSessionFactory().getStatistics());
		setCacheHits(empImpl.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
		return SUCCESS;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public long getCacheHits() {
		return cacheHits;
	}

	public void setCacheHits(long cacheHits) {
		this.cacheHits = cacheHits;
	}



	


	
}
