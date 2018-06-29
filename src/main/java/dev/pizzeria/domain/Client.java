package dev.pizzeria.domain;

import java.util.UUID;

/**
 * Client.java
 * @author matth
 */
public class Client {
	
	private UUID uuid;
	private String nom;
	private String prenom;
	private String ville;
	private int age;
	



	/**
	 * Constructor of Client.java
	 */
	public Client() {
		super();
	}

	

	/**
	 * Constructor of Client.java
	 * @param uuid
	 * @param nom
	 * @param prenom
	 * @param ville
	 * @param age
	 */
	public Client(UUID uuid, String nom, String prenom, String ville, int age) {
		super();
		this.uuid = uuid;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
	}
	
	/** 
	 * Fonction :  
	 * @return
	 */
	public UUID getUuid() {
		return uuid;
	}

	/** 
	 * Fonction :  
	 * @param uuid
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/** 
	 * Fonction :  
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/** 
	 * Fonction :  
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}



	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}



	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	

}
