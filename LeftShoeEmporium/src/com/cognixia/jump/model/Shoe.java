package com.cognixia.jump.model;

import java.io.Serializable;

public class Shoe implements Serializable {

	private static final long serialVersionUID = 1L;

	private long shoe_id;

	private String brand;
	private String shoe_name;
	private String shoe_type;

	private float price;

	private int stock;

	public Shoe() {
		this("N/A", "N/A", "N/A", 0f, 0);
		this.shoe_id = -1L;
	}

	public Shoe(String brand, String shoe_name, String shoe_type, float price, int stock) {
		super();
		this.shoe_id = -1L;
		this.brand = brand;
		this.shoe_name = shoe_name;
		this.shoe_type = shoe_type;
		this.price = price;
		this.stock = stock;
	}

	public long getShoe_id() {
		return shoe_id;
	}

	public void setShoe_id(long shoe_id) {
		this.shoe_id = shoe_id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getShoe_name() {
		return shoe_name;
	}

	public void setShoe_name(String shoe_name) {
		this.shoe_name = shoe_name;
	}

	public String getShoe_type() {
		return shoe_type;
	}

	public void setShoe_type(String shoe_type) {
		this.shoe_type = shoe_type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Shoe [shoe_id=" + shoe_id + ", brand=" + brand + ", shoe_name=" + shoe_name + ", shoe_type=" + shoe_type
				+ ", price=" + price + ", stock=" + stock + "]";
	}

}
