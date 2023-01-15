package com.melihcanozturk.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductEvaluate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String comment;

	private double point;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Product product;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Customer customer;

	public ProductEvaluate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductEvaluate(String comment, double point, Product product, Customer customer) {
		super();
		this.comment = comment;
		this.point = point;
		this.product = product;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ProductEvaluate [comment=" + comment + ", point=" + point + "]";
	}

	

}
