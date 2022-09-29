package com.cognixia.jump.UI;

import java.util.Scanner;

import com.cognixia.jump.dao.UserDAO;
import com.cognixia.jump.model.User;

public class RegisterUI {

	public static void registerUser(Scanner scan) {

		User user = new User();
		UserDAO userDAO = new UserDAO();
		boolean exitStatus = false;

		while (!exitStatus) {
			System.out.println("\nPlease enter your username: ");
			user.setUsername(scan.next());

			System.out.println("\nPlease enter your password: ");// Validate input
			user.setPassword(scan.next());

			System.out.println("\nPlease enter your first name: ");
			user.setFirst_name(scan.next());

			System.out.println("\nPlease enter your last name: ");
			user.setLast_name(scan.next());

			System.out.println("\nPlease enter your email: ");// Validate input
			user.setEmail(scan.next());

			boolean confirmed = false;

			while (!confirmed) {
				System.out.println("Is \n" + user + " correct? (Y/N)");
				String response = scan.next();

				if (response.equalsIgnoreCase("y")) {
					exitStatus = true;
					userDAO.create(user);
					System.out.println("User profile created!");
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
