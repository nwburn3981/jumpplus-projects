package com.cognixia.jump.UI;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.jump.dao.OrderDAO;
import com.cognixia.jump.dao.ShoeDAO;
import com.cognixia.jump.model.Cart;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Shoe;

public class OrderUI {

	private static OrderDAO orderDAO = new OrderDAO();
	private static ShoeDAO shoeDAO = new ShoeDAO();

	public static void viewCart(Scanner scan) {

		System.out.println("\nCurrent cart: ");

		ConsoleUI.cart.getShoes().forEach(System.out::println);

		System.out.println("\nPress enter to exit.....");

		// Twice to clear scanner
		scan.nextLine();
		scan.nextLine();

	}

	public static void viewOrders(Scanner scan, int id) {

		System.out.println("\n Order History: ");

		ArrayList<Order> orders = orderDAO.findOrdersByUser(id);

		for (Order order : orders) {
			ArrayList<Shoe> shoes = orderDAO.findShoesOnOrder((int) order.getOrder_id());

			System.out.println("\n" + order);
			shoes.forEach(System.out::println);
		}

		System.out.println("\nPress enter to exit.....");

		// Twice to clear scanner
		scan.nextLine();
		scan.nextLine();

	}

	public static void submitOrder(Scanner scan, int id) {

		ArrayList<Shoe> shoes = ConsoleUI.cart.getShoes();

		// Collect total price of all shoes in cart
		double total = 0;

		for (Shoe shoe : shoes) {
			total += shoe.getPrice();
		}

		if (shoes != null) {

			boolean confirmed = false;
			while (!confirmed) {
				System.out.println("Submit your order? (Y/N)");
				String response = scan.next();

				if (response.equalsIgnoreCase("y")) {

					Order order = new Order(Date.valueOf(LocalDate.now()), id, total);

					order = orderDAO.create(order);

					System.out.println("WHHHHHYYYYYY " + order.getOrder_id());

					for (Shoe shoe : shoes) {
						orderDAO.createRelationship((int) order.getOrder_id(), (int) shoe.getShoe_id());
						shoe.setStock(shoeDAO.findStock(shoe) - 1);
						shoeDAO.updateStock(shoe);
					}

					ConsoleUI.cart = new Cart();

					confirmed = true;
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

	public static void returnOrder(Scanner scan) {

	}

}
