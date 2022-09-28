package com.cognixia.jump.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognixia.jump.dao.ShoeDAO;
import com.cognixia.jump.model.Shoe;

public class ShoeUI {

	private static ShoeDAO shoeDAO = new ShoeDAO();

	public static void addShoeView(Scanner scan) {

		ArrayList<Shoe> shoes = shoeDAO.findAll();

		boolean exitStatus = false;
		while (!exitStatus) {
			shoes.forEach(System.out::println);

			System.out.println("\nPlease select the shoe code of the item you wish to add to your cart: ");

			scan.nextLine();
			String input = scan.nextLine();

			// Reduces shoe list to single matching shoe code, need to refine
			List<Shoe> shoe = shoes.parallelStream().filter(s -> s.getShoe_code().equals(input))
					.collect(Collectors.toList());

			boolean confirmed = false;
			while (!confirmed) {
				System.out.println("\nIs " + shoe.get(0) + " correct? (Y/N)");
				String response = scan.next();

				if (response.equalsIgnoreCase("y")) {
					ConsoleUI.cart.addShoe(shoe.get(0));
					System.out.println("\n" + shoe.get(0) + " added!");
					confirmed = true;
					exitStatus = true;
					break;
				} else if (response.equalsIgnoreCase("n")) {
					confirmed = true;
					break;
				} else {
					System.out.println("\nNot a valid input.");
				}
			}
		}
	}

	public static void removeShoeView(Scanner scan) {

		List<Shoe> shoes = ConsoleUI.cart.getShoes();

		boolean exitStatus = false;
		
		while (!exitStatus) {
			shoes.forEach(System.out::println);

			System.out.println("\nPlease select the shoe code of the item you wish to remove from your cart: ");

			scan.nextLine();
			String input = scan.nextLine();

			// Reduces shoe list to single matching shoe code, need to refine
			List<Shoe> shoe = shoes.parallelStream().filter(s -> s.getShoe_code().equals(input))
					.collect(Collectors.toList());

			boolean confirmed = false;
			while (!confirmed) {
				System.out.println("\nIs " + shoe.get(0) + " correct? (Y/N)");
				String response = scan.next();

				if (response.equalsIgnoreCase("y")) {
					ConsoleUI.cart.removeShoe(shoe.get(0));
					System.out.println("\n" + shoe.get(0) + " removed!");
					confirmed = true;
					exitStatus = true;
					break;
				} else if (response.equalsIgnoreCase("n")) {
					confirmed = true;
					break;
				} else {
					System.out.println("\nNot a valid input.");
				}
			}
		}

	}

}
