package dev.pizzeria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="LABEL")
	private String label;
	
	@Column(name="REFFERENCE")
	private String refference;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="IMAGE")
	private String imageUrl;
	
	public Pizza(){
		
		
	}
	
	public Pizza(String label, String refference, double price, String imageUrl){
		this.label = label;
		this.refference = refference;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRefference() {
		return refference;
	}

	public void setRefference(String refference) {
		this.refference = refference;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
