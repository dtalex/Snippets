package org.rao.user.jpa_gui.generic_beans;

public class MyResult {
	public enum ReturnTypes{
		ERROR, WARNING, OK;
	}
	
	private ReturnTypes outcome;
	private String message;
	
	
	
	public MyResult(ReturnTypes outcome, String message) {
		this.outcome = outcome;
		this.message = message;
	}
	
	public ReturnTypes getOutcome() {
		return outcome;
	}
	public void setOutcome(ReturnTypes outcome) {
		this.outcome = outcome;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

