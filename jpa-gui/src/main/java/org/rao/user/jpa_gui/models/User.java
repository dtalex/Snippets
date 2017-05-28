package org.rao.user.jpa_gui.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	private String nome;
	private String cognome;
	
	@Id
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
