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




	public Client(UUID uuid, String nom, String prenom, String ville, Integer age) {
		super();
		this.uuid = uuid;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
	}




	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}




	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}




	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}




	/**
	 * @param nom the nom to set
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
	public Integer getAge() {
		return age;
	}




	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}

	

	