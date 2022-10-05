package com.cognixia.jump.view;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;

import com.cognixia.jump.dao.AccountDAO;
import com.cognixia.jump.dao.TransactionDAO;
import com.cognixia.jump.dao.UserDAO;
import com.cognixia.jump.exception.RecordNotFoundException;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.model.User;
import com.cognixia.jump.validation.Validation;

public class LandingView {

	public static int id = 0;

	public static UserDAO userDAO = new UserDAO();
	public static AccountDAO accountDAO = new AccountDAO();
	public static TransactionDAO transDAO = new TransactionDAO();

	public static void landingView(Scanner scan) {

		AnsiConsole.systemInstall();

		boolean exitStatus = false;

		System.out.println(ansi().eraseScreen().fgBlue().a("********************************"));
		System.out.println("*      Moonlighter's Bank      *");
		System.out.println("********************************");

		System.out.println(ansi().fgRgb(67, 144, 186));

		while (!exitStatus) {
			if (id == 0) {

				System.out.println("\n1. Create New User Account" + "\n2. Login" + "\n3. Exit");

				int choice = Validation.numberValidation(scan, "^[123]{1}$");

				System.out.println(ansi().fgRgb(67, 144, 186));

				switch (choice) {
				case 1:
					newAccountView(scan);
					break;
				case 2:
					loginView(scan);
					break;
				case 3:
					System.out.println("Thank you for using Moonlighter!");
					exitStatus = true;
					break;
				}

			} else {
				System.out.println(id);
				MainMenuView.mainMenuView(scan, id);
				id = 0;
			}

		}

	}

	public static void newAccountView(Scanner scan) {

		boolean confirmed = false;

		User user = new User();
		Account account = new Account();
		Transaction trans = new Transaction();

		while (!confirmed) {
			System.out.println(ansi().eraseScreen().fgBlue().a("********************************"));
			System.out.println("*      Create New Account      *");
			System.out.println("********************************");

			System.out.println(ansi().fgRgb(67, 144, 186));

			System.out.println("Please enter your name: ");

			String name = scan.nextLine();
			user.setName(name);

			System.out.println("\nPlease enter your username: ");

			String username = scan.nextLine();
			user.setUsername(username);

			System.out.println("\nPlease enter your password: ");

			String password = Validation.passwordValidation(scan);
			user.setPassword(password);

			System.out.print(ansi().fgRgb(67, 144, 186));

			System.out.println("\nPlease enter your city: ");

			String city = scan.nextLine();
			user.setCity(city);

			System.out.println("\nPlease enter your street address: ");

			String street = scan.nextLine();
			user.setStreet(street);

			System.out.println("\nPlease enter your phone number: ");

			String number = scan.nextLine();
			user.setPhoneNumber(number);

			System.out.println(user);
			System.out.println(ansi().fgRgb(67, 144, 186).a("\nIs this information correct? (Y/N)"));

			String answer = Validation.binaryValidation(scan, "^[YNyn]$");

			System.out.print(ansi().fgRgb(67, 144, 186));

			if (answer.equalsIgnoreCase("y")) {
				userDAO.createUser(user);
				confirmed = true;
			}

		}

		confirmed = false;

		while (!confirmed) {

			System.out.println(ansi().eraseScreen().fgBlue().a("**********************"));
			System.out.println("*     New Account    *");
			System.out.println("**********************");

			System.out.println(ansi().fgRgb(67, 144, 186));

			System.out.println("\nPlease choose an account type: (C)hecking or (S)avings");

			String choice = Validation.binaryValidation(scan, "^[CScs]$");

			if (choice.equalsIgnoreCase("c")) {
				account.setAccountType("CHECKING");
			} else {
				account.setAccountType("SAVINGS");
			}

			System.out.println("\nPlease enter an inital deposit:");

			int balance = Validation.numberValidation(scan, "^[1-9][\\d]*$");

			account.setBalance(balance);

			account.setUserId((int) userDAO.getUserByName(user.getName()).getId());

			System.out.println("\n" + account);
			System.out.println(ansi().fgRgb(67, 144, 186).a("\nIs this information correct? (Y/N)"));

			choice = Validation.binaryValidation(scan, "^[YNyn]$");

			System.out.print(ansi().fgRgb(67, 144, 186));

			if (choice.equalsIgnoreCase("y")) {

				accountDAO.createAccount(account);
				confirmed = true;

				trans.setDescription("Initial deposit - $" + balance);

				trans.setInitialAccountId((int) accountDAO.findAccountByTimestamp(account.getCreated()).getId());
				trans.setEndAccountId((int) accountDAO.findAccountByTimestamp(account.getCreated()).getId());

				transDAO.createTransaction(trans);
			}

		}
	}

	public static void loginView(Scanner scan) {

		System.out.println("\nPlease enter your username: ");

		String username = scan.nextLine();

		System.out.println("\nPlease enter your password: ");

		String password = scan.nextLine();

		try {

			id = userDAO.verifyUser(username, password);

			if (id == 0) {
				throw new RecordNotFoundException("Bad credentials");
			}
		} catch (RecordNotFoundException e) {
			System.out.println(ansi().fgBrightYellow().a("Review your credentials or create new account."));
			System.out.print(ansi().fgRgb(67, 144, 186));
		}

	}

}
