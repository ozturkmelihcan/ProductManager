package com.melihcanozturk.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;

@Entity
public class Customer extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String password;

	private String identity;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Product> product;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<ProductEvaluate> productEvaluates;

	public Customer() {
		super();

	}

	public Customer(String firstName, String lastName, String email, String password, String identity,
			List<Product> product, List<ProductEvaluate> productEvaluates) {
		super(firstName, lastName, email);
		this.password = password;
		this.identity = identity;
		this.product = product;
		this.productEvaluates = productEvaluates;
	}

	public Customer(String firstName, String lastName, String email, String password, String identity) {
		super(firstName, lastName, email);
		this.password = password;
		this.identity = identity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<ProductEvaluate> getProductEvaluates() {
		return productEvaluates;
	}

	public void setProductEvaluates(List<ProductEvaluate> productEvaluates) {
		this.productEvaluates = productEvaluates;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", password=" + password + ", identity=" + identity + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + "]";
	}

}
