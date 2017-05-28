package org.rao.user.jpa_gui.interceptors;

import org.rao.user.jpa_gui.abstracts.MyAction;
import org.rao.user.jpa_gui.db.DBProvider;
import org.rao.user.jpa_gui.generic_beans.MyResult.ReturnTypes;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class DBInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 913056809649072405L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		MyAction myAction = (MyAction) arg0.getAction();
		myAction.setCurrentAction();
		DBProvider.getInstance();
		DBProvider.getEntityManager();
		String result = ReturnTypes.ERROR.name();
		try {
			DBProvider.beginTransaction();
			result = arg0.invoke();
			if (myAction.getResult().getOutcome() == ReturnTypes.ERROR)
				DBProvider.rollback();
			else {
				DBProvider.commit();
			}
		} finally {
			DBProvider.closeEntityManager();
		}

		return result;
	}

}
