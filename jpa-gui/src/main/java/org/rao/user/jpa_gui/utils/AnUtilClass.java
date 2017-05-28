package org.rao.user.jpa_gui.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.rao.user.jpa_gui.abstracts.MyAction;
import org.rao.user.jpa_gui.db.DBProvider;
import org.rao.user.jpa_gui.generic_beans.MyResult;
import org.rao.user.jpa_gui.generic_beans.MyResult.ReturnTypes;
import org.rao.user.jpa_gui.models.User;



public class AnUtilClass {
	public static List<User> listAllUsers(){
		
			EntityManager em = DBProvider.getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<User> cq = cb.createQuery(User.class);
	        Root<User> rootEntry = cq.from(User.class);
	        CriteriaQuery<User> all = cq.select(rootEntry);
	        TypedQuery<User> allQuery = em.createQuery(all);

			MyAction currentAction = MyAction.getCurrentAction();
			currentAction.setResult(new MyResult(ReturnTypes.OK, "success"));

			return allQuery.getResultList();
	}
}
