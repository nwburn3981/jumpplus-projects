package com.cognixia.jump.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Test;

import com.cognixia.jump.UI.ConsoleUI;
import com.cognixia.jump.model.Cart;
import com.cognixia.jump.model.Shoe;

public class Tests {
	
	@Test
	public void totalTest() {
		
		Cart cart = new Cart();
		
		Shoe shoe1 = new Shoe();
		shoe1.setPrice(11.26);
		
		Shoe shoe2 = new Shoe();
		shoe2.setPrice(12.26);
		
		Shoe shoe3 = new Shoe();
		shoe3.setPrice(13.26);
		
		Shoe shoe4 = new Shoe();
		shoe4.setPrice(14.26);
		
		cart.addShoe(shoe1);
		cart.addShoe(shoe2);
		cart.addShoe(shoe3);
		cart.addShoe(shoe4);
		
		//System.out.println(cart.getShoes());
		
		ArrayList<Shoe> shoes = cart.getShoes();

		//Collect total price of all shoes in cart
		double total = 0;
		
		for (Shoe shoe : shoes) {
			total += shoe.getPrice();
		}
		
		
		assertEquals(51.04, total);
		
		
	}

}
