package com.cognixia.jump.UI;

import java.util.Scanner;

public class ConsoleUI {

	public static int id = 0;

	public static void mainMenu(Scanner scan) {

		boolean exitStatus = false;

		System.out.println("Welcome to Left Shoe Emporium");

		while (exitStatus == false) {
			if (id == 0) {

				System.out.println("\nPlease login or register");

				System.out.println("\n1. Register New User" + "\n2. Login" + "\n3. Exit");
				
				scan.next();

			} else {

				System.out.println("\nPlease choose a menu option:");

				System.out.println("\n1. Add Shoes" + "\n2.Remove Shoes" + "\n3. View Cart" + "\n4. View Order History"
						+ "\n5. Checkout" + "\n6. Logout");
				
				scan.next();
			}

		}
	}

}
