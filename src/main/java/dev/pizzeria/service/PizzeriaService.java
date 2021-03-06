package dev.pizzeria.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.pizzeria.domain.Client;

public class PizzeriaService {
	
	Map<UUID, Client> clients = new HashMap<>();

	public void sauverClient(String nom) {
		Client client = new Client(UUID.randomUUID(), nom);
		this.clients.put(client.getUuid(), client);
	}
	
}
