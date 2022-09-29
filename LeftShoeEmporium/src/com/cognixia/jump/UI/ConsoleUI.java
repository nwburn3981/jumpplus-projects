package com.cognixia.jump.UI;

import java.util.Scanner;

import com.cognixia.jump.dao.UserDAO;
import com.cognixia.jump.model.Cart;
import com.cognixia.jump.model.User;

//TODO input validation, String formatting

public class ConsoleUI {

	public static int id = 0;
	
	public static Cart cart;

	public static UserDAO userDAO = new UserDAO();

	public static void mainMenu(Scanner scan) {

		boolean exitStatus = false;

		System.out.println("Welcome to Left Shoe Emporium");

		while (exitStatus == false) {
			
			
			
			if (id == 0) {

				System.out.println("\nPlease login or register");

				System.out.println("\n1. Register New User" + "\n2. Login" + "\n3. Exit");

				int userChoice = scan.nextInt();

				switch (userChoice) {
				case 1:
					// Register User
					RegisterUI.registerUser(scan);
					break;
				case 2:
					id = login(scan);
					if (id == 0) {
						System.out.println("\nBad credentials. Please Try again.");
					}
					//Initiates new Cart whenever a user logs in
					cart = new Cart();
					break;
				case 3:
					System.out.println("Thanks for shopping Left Shoe!");
					exitStatus = true;
					break;
				default:
					System.out.println("Unexpected value: " + userChoice + "\nPlease choose an option by number.");
					break;
				}

			} else {

				System.out.println("\nPlease choose a menu option:");

				System.out.println("\n1. Add Shoes" + "\n2. Remove Shoes" + "\n3. View Cart" + "\n4. View Order History"
						+ "\n5. Checkout" + "\n6. Return an item " + "\n7. Logout");

				int userChoice = scan.nextInt();
				

				switch (userChoice) {
				case 1:
					// Add Shoe view
					ShoeUI.addShoeView(scan);
					break;
				case 2:
					// Remove shoe view
					ShoeUI.removeShoeView(scan);
					break;
				case 3:
					// View Cart
					OrderUI.viewCart(scan);
					break;
				case 4:
					// View Order History
					OrderUI.viewOrders(scan, id);
					break;
				case 5:
					// Checkout confirmation
					OrderUI.submitOrder(scan, id);
					break;
				case 6:
					// Return an item before 15 days
					OrderUI.returnOrder(scan, id);
					break;
				case 7:
					boolean confirmed = false;
					
					//clear up scanner
					scan.nextLine(); 

					while (!confirmed) {
						System.out.println("Logout? You will lose any items in your cart (Y/N)");
						String response = scan.next();

						if (response.equalsIgnoreCase("y")) {
							id = 0;
							confirmed = true;
							break;
						} else if (response.equalsIgnoreCase("n")) {
							confirmed = true;
							break;
						} else {
							System.out.println("\nNot a valid input.");
						}
					}
				default:
					System.out.println("Unexpected value: " + userChoice + "\nPlease choose an option by number.");
					break;
				}
			}

		}
	}

	public static int login(Scanner scan) {

		User user = new User();

		System.out.println("\nPlease enter your username: ");

		user.setUsername(scan.next());

		System.out.println("\nPlease enter your password: ");

		user.setPassword(scan.next());

		return userDAO.verifyUser(user);

	}

}
