package com.cognixia.jump.validation;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.jump.model.Account;

public class Validation {

	public static int numberValidation(Scanner scan, String regex) {

		String choice = scan.next();

		while (!choice.matches(regex)) {
			System.out.println(ansi().fgRed().a("\nNot a valid choice, please choose from menu options."));
			choice = scan.next();

		}

		scan.nextLine();
		
		System.out.print(ansi().fgRgb(67, 144, 186));

		return Integer.parseInt(choice);

	}

	public static int accountValidation(Scanner scan, ArrayList<Account> accounts) {

		boolean validated = false;

		String choice = scan.nextLine();

		while (!validated) {
			for (Account account : accounts) {

				if (Integer.parseInt(choice) == (int) account.getId()) {
					validated = true;
				}
			}

			if (!validated) {
				System.out.println("Does not match accounts, please try again: ");

				for (Account account : accounts) {
					System.out.println(account);
				}

				choice = scan.nextLine();

			}

		}
		
		System.out.print(ansi().fgRgb(67, 144, 186));

		return Integer.parseInt(choice);

	}

	public static String passwordValidation(Scanner scan) {

		String choice = scan.nextLine();
		boolean validated = false;

		System.out.print(ansi().fgRed());

		while (!validated) {

			if (choice.length() < 8) {
				System.out.println("\nToo short, password must be at least 8 characters. Please try again: ");
				choice = scan.nextLine();
			} else if (choice.matches("^[^0-9()]+$")) {
				System.out.println("\nNo digits, password must have at least 1 digit 0-9. Please try again: ");
				choice = scan.nextLine();
			} else {
				validated = true;
			}
		}
		
		System.out.print(ansi().fgRgb(67, 144, 186));

		return choice;
	}

	public static String binaryValidation(Scanner scan, String regex) {

		String choice = scan.nextLine();

		System.out.print(ansi().fgRed());

		while (!choice.matches(regex)) {

			System.out.println("Not valid choice.");
			choice = scan.nextLine();
		}
		
		System.out.print(ansi().fgRgb(67, 144, 186));

		return choice;
	}

	public static double withdrawValidation(Scanner scan, double balance) {

		boolean validated = false;

		System.out.println("\nWithdraw amount: ");

		String withdraw = scan.nextLine();
		
		while (!validated) {
			if (balance - Double.parseDouble(withdraw) < -1) {
				System.out.println("Not enough funds to withdraw.");
				System.out.println("Balance: " + balance);
				System.out.println("Please choose new withdraw amount: ");
				
				withdraw = scan.nextLine();
			}
			else {
				validated = true;
			}
		}
		
		System.out.print(ansi().fgRgb(67, 144, 186));
		
		return balance - Double.parseDouble(withdraw);
	}

}
