package com.cognixia.jump.validation;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.Scanner;

public class Validation {

	public static int numberValidation(Scanner scan, String regex) {

		String choice = scan.next();

		while (!choice.matches(regex)) {
			System.out.println(ansi().fgRed().a("\nNot a valid choice, please choose from menu options."));
			choice = scan.next();

		}
		
		scan.nextLine();

		return Integer.parseInt(choice);

	}

	public static String passwordValidation(Scanner scan) {

		String choice = null;
		boolean validated = false;

		System.out.print(ansi().fgRed());

		while (!validated) {
			choice = scan.nextLine();
			if (choice.length() < 8) {
				System.out.println("\nToo short, password must be at least 8 characters. Please try again: ");
			} else if (choice.matches("^[^0-9()]+$")) {
				System.out.println("\nNo digits, password must have at least 1 digit 0-9. Please try again: ");
			} else {
				validated = true;
			}
		}

		return choice;
	}

	public static String binaryValidation(Scanner scan, String regex) {

		String choice = scan.nextLine();

		System.out.print(ansi().fgRed());

		while (!choice.matches(regex)) {

			System.out.println("Not valid choice.");
			choice = scan.nextLine();
		}

		return choice;
	}

}
