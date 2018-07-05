package dev.pizzeria.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Commande 
{
	private UUID uuid;
	private static Integer increment = new Integer(0);
	private Integer numeroCommande;
	private LocalDateTime dateCommande = LocalDateTime.now();
	private Client client;
	private Livreur livreur;
	
	
	public Commande()
	{
		this.numeroCommande = ++increment;
	}
	
	public Commande(UUID uuid, Client client, Livreur livreur)
	{
		this.uuid = uuid;
		this.numeroCommande = ++increment;
		this.client = client;
		this.livreur = livreur;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getNumeroCommande() {
		return numeroCommande;
	}
	
	public void setNumeroCommande(Integer numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public LocalDateTime getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	
}
