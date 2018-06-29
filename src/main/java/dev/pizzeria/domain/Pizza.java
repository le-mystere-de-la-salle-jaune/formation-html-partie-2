package dev.pizzeria.domain;

import java.util.UUID;

public class Pizza {
	private UUID uuid;
	private String libelle;
	private String reference;
	private double prix;
	private String url;
	
	public Pizza(UUID uuid, String libelle, String reference, double prix, String url) {
		super();
		this.uuid = uuid;
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
