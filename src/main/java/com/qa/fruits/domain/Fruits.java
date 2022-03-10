package com.qa.fruits.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Fruits {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	@Column(unique = true)
	private Integer quantity;
	
	private Boolean seed;
	
	

	public Fruits() {
		super();
	}

	public Fruits(Integer id, String name, Integer quantity, Boolean seed) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.seed = seed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getSeed() {
		return seed;
	}

	public void setSeed(Boolean seed) {
		this.seed = seed;
	}

	@Override
	public String toString() {
		return "Fruits [id=" + id + ", quantity=" + quantity + ", seed=" + seed + "]";
	}
}
