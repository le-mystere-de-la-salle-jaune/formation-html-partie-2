package dev.pizzeria.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Livreur 
{
	private UUID uuid;
	private String nom;
	private String prenom;
	private String telephone;
	private LocalDate dateEmbauche;
	
	public Livreur()
	{
		
	}
	
	public Livreur(UUID pUuid, String pNom, String pPrenom, String pTelephone, LocalDate pDateEmbauche)
	{
		this.uuid = pUuid;
		this.nom = pNom;
		this.prenom = pPrenom;
		this.telephone = pTelephone;
		this.dateEmbauche = pDateEmbauche;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	
	
	
}
