package com.cognixia.jump.model;

import java.util.ArrayList;

//TODO Add to DB for saving between sessions, need relationship table shoe = cart

public class Cart {
	
	private ArrayList<Shoe> shoes;
	
	public Cart() {
		this(new ArrayList<Shoe>());
	}

	public Cart(ArrayList<Shoe> shoes) {
		super();
		this.shoes = shoes;
	}

	public ArrayList<Shoe> getShoes() {
		return shoes;
	}

	public void setShoes(ArrayList<Shoe> shoes) {
		this.shoes = shoes;
	}
	
	public void addShoe(Shoe shoe) {
		this.shoes.add(shoe);
	}
	
	public void removeShoe(Shoe shoe) {
		this.shoes.remove(shoe);
	}

	@Override
	public String toString() {
		return "Cart [shoes=" + shoes + "]";
	}

}
