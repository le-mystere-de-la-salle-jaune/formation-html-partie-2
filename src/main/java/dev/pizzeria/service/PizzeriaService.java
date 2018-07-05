package dev.pizzeria.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Commande;
import dev.pizzeria.domain.Livreur;
import dev.pizzeria.domain.Pizza;

public class PizzeriaService {
	
	Map<UUID, Client> clients = new HashMap<>();
	Map<UUID, Pizza> pizzas = new HashMap<>();
	Map<UUID, Livreur> livreurs = new HashMap<>();
	Map<UUID, Commande> commandes = new HashMap<>();
	

	public void sauverClient(String nom, String prenom, String ville, Integer age) 
	{
		Client client = new Client(UUID.randomUUID(), nom, prenom, ville, age);
		this.clients.put(client.getUuid(), client);
	}
	
	public Map<UUID, Client> listerClients()
	{
		return this.clients;
	}
		
	public void sauverPizza(String pLibelle, String pReference, double pPrix, String pUrl) 
	{
		Pizza pizza = new Pizza(UUID.randomUUID(), pLibelle, pReference, pPrix, pUrl);
		this.pizzas.put(pizza.getUuid(), pizza);
	}
	
	public Map<UUID, Pizza> listerPizzas()
	{
		return this.pizzas;
	}
	
	public void sauverLivreur(String pNom, String pPrenom, String pTelephone, LocalDate pDateEmbauche) 
	{
		Livreur livreur = new Livreur(UUID.randomUUID(), pNom, pPrenom, pTelephone, pDateEmbauche);
		this.livreurs.put(livreur.getUuid(), livreur);
	}
	
	public Map<UUID, Livreur> listerLivreurs()
	{
		return this.livreurs;
	}
	
	public Client trouverClientParNomPrenom(String nomClient, String prenomClient)
	{
		Map<UUID, Client> tempMapClient = listerClients();
		Set<UUID> setUuid = tempMapClient.keySet();
         
         for(UUID u : setUuid)
         {
         	Client c = tempMapClient.get(u);
         	
         	if(c.getNom().equals(nomClient))
         	{
         		if(c.getPrenom().equals(prenomClient))
         		{
         			return c;
         		}
         	}
         }
         return null;
	}
	
	public Livreur trouverLivreurParNomPrenom(String nomLivreur, String prenomLivreur)
	{
		Map<UUID, Livreur> tempMapLivreur = listerLivreurs();
		Set<UUID> setUuid = tempMapLivreur.keySet();
         
         for(UUID u : setUuid)
         {
         	Livreur l = tempMapLivreur.get(u);
         	
         	if(l.getNom().equals(nomLivreur))
         	{
         		if(l.getPrenom().equals(prenomLivreur))
         		{
         			return l;
         		}
         	}
         }
         return null;
	}
	
	public void sauverCommande(String nomClient, String prenomClient, String nomLivreur, String prenomLivreur) 
	{
		Client c = trouverClientParNomPrenom(nomClient, prenomClient);
		Livreur l = trouverLivreurParNomPrenom(nomLivreur, prenomLivreur);
		
		Commande commande = new Commande (UUID.randomUUID(),c,l);
		this.commandes.put(commande.getUuid(), commande);
	}
	
	public Map<UUID, Commande> listerCommandes()
	{
		return this.commandes;
	}
	
	public void modifierCommande(Commande commande, String nomClient, String prenomClient, String nomLivreur, String prenomLivreur)
	{
		Commande tempCommande = this.commandes.get(commande.getUuid());
		Client client = trouverClientParNomPrenom(nomClient, prenomClient);
		Livreur livreur = trouverLivreurParNomPrenom(nomLivreur, prenomLivreur);
		
		tempCommande.setClient(client);
		tempCommande.setLivreur(livreur);
		
		this.commandes.put(commande.getUuid(), tempCommande);
		
	} 
	
	public void supprimerCommande(Commande commande)
	{
		commandes.remove(commande.getUuid());
	} 
	
	
}
