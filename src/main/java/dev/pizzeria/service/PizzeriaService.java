package dev.pizzeria.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Livreur;
import dev.pizzeria.domain.Pizza;

public class PizzeriaService {
	
	Map<UUID, Client> clients = new HashMap<>();
	Map<UUID, Pizza> pizzas = new HashMap<>();
	Map<UUID, Livreur> livreurs = new HashMap<>();

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
	
	
}
