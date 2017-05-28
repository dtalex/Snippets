package org.rao.user.jpa_gui.abstracts;

import org.rao.user.jpa_gui.generic_beans.MyResult;
import org.rao.user.jpa_gui.generic_beans.MyResult.ReturnTypes;

import com.opensymphony.xwork2.ActionSupport;

public class MyAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7445089070133206956L;
	protected MyResult result;

	public MyAction(){
		result=new MyResult(ReturnTypes.ERROR, "Unhandled error");
	}
	
	public MyResult getResult() {
		return result;
	}

	public void setResult(MyResult result) {
		this.result = result;
	}
	
	private static final ThreadLocal<MyAction> threadLocal = new ThreadLocal<>();
	public static MyAction getCurrentAction() {
		return threadLocal.get();
	}
	public void setCurrentAction(){
		threadLocal.set(this);
	}
}
