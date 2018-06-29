package dev.pizzeria.domain;

import java.util.UUID;

public class Client {
	
	private UUID uuid;
	private String nom;
	private String prenom;
	private String ville;
	private int age;
	



	public Client() {
		super();
	}

	

	public Client(UUID uuid, String nom) {
		super();
		this.uuid = uuid;
		this.nom = nom;
	}
	
	public Client(UUID uuid, String nom, String prenom, String ville, int age) {
		super();
		this.uuid = uuid;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
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



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}
	
	

}
