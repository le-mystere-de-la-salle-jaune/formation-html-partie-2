package dev.pizzeria.domain;

import java.util.UUID;

public class Client {
	
	private UUID uuid;
	private String nom;
	private String prenom;
	private String ville;
	private Integer age;

	public Client() {
		super();
	}

	

	public Client(UUID uuid, String nom) {
		super();
		this.uuid = uuid;
		this.nom = nom;
	}
	
	public Client(UUID pUuid, String pNom, String pPrenom, String pVille, Integer pAge) 
	{
		super();
		this.uuid = pUuid;
		this.nom = pNom;
		this.prenom = pPrenom;
		this.ville = pVille;
		this.age = pAge;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
