package org.rao.user.jpa_gui.actions;

import java.util.List;

import javax.persistence.EntityManager;

import org.rao.user.jpa_gui.abstracts.MyAction;
import org.rao.user.jpa_gui.db.DBProvider;
import org.rao.user.jpa_gui.generic_beans.MyResult;
import org.rao.user.jpa_gui.generic_beans.MyResult.ReturnTypes;
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

	


	
}
