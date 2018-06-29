package dev.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIVREUR")
public class Livreur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="FIRSTNAME")
	private String fisrstname;

	public Livreur(){
		
	}
	
	public Livreur(String prenom, String nom){
		fisrstname = prenom;
		lastname = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFisrstname() {
		return fisrstname;
	}

	public void setFisrstname(String fisrstname) {
		this.fisrstname = fisrstname;
	}
	
	
	
}
