package dev.pizzeria.domain;

import java.util.UUID;

public class Livreur extends Client{
	private String locomotion;

	
	
	public Livreur(UUID uuid, String nom, String prenom, String ville, int age, String locomotion) {
		super(uuid, nom, prenom, ville, age);
		this.locomotion = locomotion;
	}

	public String getLocomotion() {
		return locomotion;
	}

	public void setLocomotion(String locomotion) {
		this.locomotion = locomotion;
	}
}
