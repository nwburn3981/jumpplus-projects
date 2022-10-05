package com.cognixia.jump.view;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.jump.dao.AccountDAO;
import com.cognixia.jump.dao.TransactionDAO;
import com.cognixia.jump.dao.UserDAO;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.model.User;
import com.cognixia.jump.validation.Validation;

public class MainMenuView {

	public static UserDAO userDAO = new UserDAO();
	public static AccountDAO accountDAO = new AccountDAO();
	public static TransactionDAO tranDAO = new TransactionDAO();

	public static int userId = 0;

	public static void mainMenuView(Scanner scan, int id) {

		userId = id;

		boolean exitStatus = false;

		System.out.println(ansi().eraseScreen().fgBlue().a("********************************"));
		System.out.println("*      Moonlighter's Bank      *");
		System.out.println("********************************");

		System.out.println(ansi().fgRgb(67, 144, 186));

		while (!exitStatus) {

			System.out.println("\n1. Deposit Amount" + "\n2. Withdraw Amount" + "\n3. Funds Transfer"
					+ "\n4. View 5 Recent Transactions" + "\n5. Open New Account" + "\n6. Display Customer Information"
					+ "\n7. Log Out");

			int choice = Validation.numberValidation(scan, "^[1234567]{1}$");

			System.out.println(ansi().fgRgb(67, 144, 186));

			switch (choice) {
			case 1:
				depositView(scan);
				break;
			case 2:
				withdrawView(scan);
				break;
			case 3:
				fundsTransferView(scan);
				break;
			case 4:
				recentTransactionsView(scan);
				break;
			case 5:
				openNewAccountView(scan);
				break;
			case 6:
				customerInfoView(scan);
				break;
			case 7:
				System.out.println("Are you sure you want to log out? (Y/N)");
				String answer = Validation.binaryValidation(scan, "^[YNyn]$");

				System.out.print(ansi().fgRgb(67, 144, 186));

				if (answer.equalsIgnoreCase("y")) {
					exitStatus = true;
				}
				break;
			}

		}

	}

	public static void depositView(Scanner scan) {

		ArrayList<Account> accounts = new ArrayList<Account>();
		Account chosenAccount = new Account();
		Transaction tran = new Transaction();

		int accountId;

		double balance;
		String deposit = null;
		double total;

		System.out.println("\nChoose account by Id: ");

		accounts = accountDAO.findAllAccountsByUser(userId);

		for (Account account : accounts) {
			System.out.println(ansi().fgMagenta().a("\nId: " + account.getId()));
			System.out.println(account);
			System.out.print(ansi().fgRgb(67, 144, 186));
		}

		accountId = Validation.accountValidation(scan, accounts);
		chosenAccount = accountDAO.findAccountById(accountId);
		balance = chosenAccount.getBalance();

		System.out.println("\nDeposit amount: ");

		deposit = scan.nextLine();

		total = balance + Double.parseDouble(deposit);

		accountDAO.updateAccountBalance(accountId, total);

		tran.setDescription("Deposit - $" + deposit);

		tran.setInitialAccountId(accountId);
		tran.setEndAccountId(accountId);

		tranDAO.createTransaction(tran);

	}

	public static void withdrawView(Scanner scan) {

		ArrayList<Account> accounts = new ArrayList<Account>();
		Account chosenAccount = new Account();
		Transaction tran = new Transaction();

		int accountId;

		double balance;
		double total;

		System.out.println("\nChoose account by Id: ");

		accounts = accountDAO.findAllAccountsByUser(userId);

		for (Account account : accounts) {
			System.out.println(ansi().fgMagenta().a("\nId: " + account.getId()));
			System.out.println(account);
			System.out.print(ansi().fgRgb(67, 144, 186));
		}

		accountId = Validation.accountValidation(scan, accounts);
		chosenAccount = accountDAO.findAccountById(accountId);
		balance = chosenAccount.getBalance();

		total = Validation.withdrawValidation(scan, balance);

		accountDAO.updateAccountBalance(accountId, total);

		tran.setDescription("Withdraw - $" + (balance - total));

		tran.setInitialAccountId(accountId);
		tran.setEndAccountId(accountId);

		tranDAO.createTransaction(tran);

	}

	public static void fundsTransferView(Scanner scan) {

		ArrayList<Account> accounts = new ArrayList<Account>();
		Account initialAccount = new Account();
		Account endpointAccount = new Account();
		Transaction tran = new Transaction();

		int initialAccountId;
		int endpointAccountId;

		double initialBalance;
		double endpointBalance;
		double initialTotal;
		double endpointTotal;
		double transferAmount;

		System.out.println("\nChoose initial account by Id: ");

		accounts = accountDAO.findAllAccountsByUser(userId);

		for (Account account : accounts) {
			System.out.println(ansi().fgMagenta().a("\nId: " + account.getId()));
			System.out.println(account);
			System.out.print(ansi().fgRgb(67, 144, 186));
		}

		initialAccountId = Validation.accountValidation(scan, accounts);
		initialAccount = accountDAO.findAccountById(initialAccountId);
		initialBalance = initialAccount.getBalance();

		initialTotal = Validation.withdrawValidation(scan, initialBalance);

		transferAmount = initialBalance - initialTotal;

		System.out.println("\nChoose endpoint account by Id: ");

		accounts = accountDAO.findAllAccounts();

		for (Account account : accounts) {
			System.out.println(ansi().fgMagenta().a("\nId: " + account.getId()));
			System.out.println(account);
			System.out.print(ansi().fgRgb(67, 144, 186));
		}

		endpointAccountId = Validation.accountValidation(scan, accounts);
		endpointAccount = accountDAO.findAccountById(endpointAccountId);
		endpointBalance = endpointAccount.getBalance();

		endpointTotal = endpointBalance + transferAmount;

		accountDAO.updateAccountBalance((int) initialAccount.getId(), initialTotal);
		accountDAO.updateAccountBalance((int) endpointAccount.getId(), endpointTotal);

		tran.setDescription("Transfer - $" + transferAmount);

		tran.setInitialAccountId(initialAccountId);
		tran.setEndAccountId(endpointAccountId);

		tranDAO.createTransaction(tran);

	}

	public static void openNewAccountView(Scanner scan) {

		Account account = new Account();
		Transaction tran = new Transaction();

		boolean confirmed = false;

		while (!confirmed) {
			System.out.println("\nPlease choose an account type: (C)hecking or (S)avings");

			String choice = Validation.binaryValidation(scan, "^[CScs]$");

			if (choice.equalsIgnoreCase("c")) {
				account.setAccountType("CHECKING");
			} else {
				account.setAccountType("SAVINGS");
			}

			System.out.println("\nPlease enter an inital deposit:");

			int balance = Validation.numberValidation(scan, "^[1-9][\\d]*$");

			System.out.print(ansi().fgRgb(67, 144, 186));

			account.setBalance(balance);

			account.setUserId(userId);

			System.out.println("\n" + account);
			System.out.println(ansi().fgRgb(67, 144, 186).a("\nIs this information correct? (Y/N)"));

			choice = Validation.binaryValidation(scan, "^[YNyn]$");

			System.out.print(ansi().fgRgb(67, 144, 186));

			if (choice.equalsIgnoreCase("y")) {

				accountDAO.createAccount(account);
				confirmed = true;

				tran.setDescription("Initial deposit - $" + balance);

				tran.setInitialAccountId((int) accountDAO.findAccountByTimestamp(account.getCreated()).getId());
				tran.setEndAccountId((int) accountDAO.findAccountByTimestamp(account.getCreated()).getId());

				tranDAO.createTransaction(tran);
			}
		}

	}

	public static void recentTransactionsView(Scanner scan) {

		ArrayList<Transaction> trans = new ArrayList<Transaction>();

		boolean confirmed = false;

		trans = tranDAO.findLast5Transactions(userId);

		while (!confirmed) {
			for (Transaction transaction : trans) {
				System.out.print("\n" + transaction);
			}
			System.out.println(ansi().fgBrightYellow().a("\nPress any key to continue"));
			scan.nextLine();
			confirmed = true;

		}

	}

	public static void customerInfoView(Scanner scan) {

		User user = userDAO.getUserById(userId);
		ArrayList<Account> accounts = new ArrayList<Account>();

		boolean confirmed = false;

		while (!confirmed) {
			System.out.println(user);

			accounts = accountDAO.findAllAccountsByUser(userId);

			for (Account account : accounts) {
				System.out.println("\n" + account);
			}

			System.out.println(ansi().fgBrightYellow().a("\nPress any key to continue"));
			scan.nextLine();
			confirmed = true;

		}
	}

}
