package dev.pizzeria.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dev.pizzeria.domain.Client;

public class PizzeriaService {
	
	EntityManagerFactory emf = EntityManagerFactoryProvider.getInstance();
	EntityManager em = emf.createEntityManager();
	//Map<UUID, Client> clients = new HashMap<>();

	public void sauverClient(String nom, String prenom, String ville, String age) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(new Client(UUID.randomUUID(), nom, prenom, ville, age));
		et.commit();
	}
	
	public List<Client> getClients() {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		return query.getResultList();
	}
	
}
