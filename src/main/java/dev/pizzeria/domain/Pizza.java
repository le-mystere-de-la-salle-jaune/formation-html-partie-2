package dev.pizzeria.domain;

import java.util.UUID;

public class Pizza 
{
	private UUID uuid;
	private String libelle;
	private String reference;
	private double prix;
	private String urlImage;
	
	public Pizza()
	{
		
	}
	
	public Pizza(UUID pUuid, String pLibelle, String pReference, double pPrix, String pUrlImage)
	{
		this.uuid = pUuid;
		this.libelle = pLibelle;
		this.reference = pReference;
		this.prix = pPrix;
		this.urlImage = pUrlImage;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	

}
