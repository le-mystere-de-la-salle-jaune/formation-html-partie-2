package dev.pizzeria.domain;

import java.util.UUID;

public class Pizza 
{
	private UUID uuid;
	private String libelle;
	private String reference;
	private double prix;
	private String url;
	
	public Pizza()
	{
		
	}
	
	public Pizza(UUID pUuid, String pLibelle, String pReference, double pPrix, String pUrl)
	{
		this.uuid = pUuid;
		this.libelle = pLibelle;
		this.reference = pReference;
		this.prix = pPrix;
		this.url = pUrl;
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
		return url;
	}

	public void setUrlImage(String url) {
		this.url = url;
	}
	
	

}
