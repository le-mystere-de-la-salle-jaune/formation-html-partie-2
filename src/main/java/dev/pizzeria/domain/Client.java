package dev.pizzeria.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {
	
	//private UUID uuid;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="AGE")
	private int age;



	public Client() {
		super();
	}

	public Client(String nom, String prenom, String ville, int age) {
		super();
		//this.uuid = uuid;
		this.firstName = prenom;
		lastName = nom;
		city = ville;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int uuid) {
		this.id = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String nom) {
		this.firstName = nom;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	

}
