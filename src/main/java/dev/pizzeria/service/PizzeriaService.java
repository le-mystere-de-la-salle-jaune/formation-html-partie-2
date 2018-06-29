package dev.pizzeria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Pizza;

public class PizzeriaService implements IPizzeriaService {
	
	Map<UUID, Client> clients = new HashMap<>();

	public void sauverClient(String firstName, String lastName, String city, int age) {
		Client client = new Client(firstName, lastName, city, age);
		//this.clients.put(client.getId(), client);
	}

	@Override
	public void saveClient(Client client) {
		// TODO Auto-generated method stub
		//this.clients.put(client.getId(), client);
	}

	@Override
	public boolean clientExists(int idClient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pizza> findAllPizza() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
