package com.cognixia.jump.validation;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.Scanner;

public class Validation {

	public static int numberValidation(Scanner scan, String regex) {

		String choice = scan.next();

		while (!choice.matches(regex)) {
			System.out.println(ansi().fgRed().a("Not a valid choice, please choose from menu options."));
			choice = scan.next();

		}

		return Integer.parseInt(choice);

	}

	public static String passwordValidation(Scanner scan) {

		String choice = null;
		boolean validated = false;

		System.out.print(ansi().fgRed());

		while (!validated) {
			choice = scan.nextLine();
			if (choice.length() < 8) {
				System.out.println("Too short, password must be at least 8 characters.");
				scan.nextLine();
			} else if (choice.matches("^[^0-9()]+$")) {
				System.out.println("No digits, password must have at least 1 digit 0-9.");
				scan.nextLine();
			} else {
				validated = true;
			}
		}

		return choice;
	}

	public static String binaryValidation(Scanner scan, String regex) {

		String choice = null;

		System.out.print(ansi().fgRed());

		while (!choice.matches(regex)) {
			choice = scan.nextLine();

			System.out.println("Not valid choice.");
			scan.nextLine();
		}

		return choice;
	}

}
