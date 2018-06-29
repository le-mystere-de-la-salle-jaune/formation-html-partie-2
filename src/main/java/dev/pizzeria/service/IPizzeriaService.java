package dev.pizzeria.service;

import java.util.ArrayList;
import java.util.List;

import dev.pizzeria.domain.Client;
import dev.pizzeria.domain.Livreur;
import dev.pizzeria.domain.Pizza;

public interface IPizzeriaService {

	default void sauverClient(String firstname, String lastname, String city, int age){
		saveClient(new Client(firstname,lastname,city,age));
	};
	public void saveClient(Client client);
	public boolean clientExists(int idClient);
	public List<Client> findAllClients();
	public void saveNewPizza(Pizza pizza);
	public List<Pizza> findAllPizza();
	public void saveNewLivreur(Livreur livreur);
}
