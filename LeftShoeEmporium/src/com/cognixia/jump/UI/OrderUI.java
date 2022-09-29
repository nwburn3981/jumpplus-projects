package com.cognixia.jump.UI;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

		System.out.println("\nOrder History: ");

		ArrayList<Order> orders = orderDAO.findOrdersByUser(id);

		for (Order order : orders) {

			System.out.println();
			
			order.generateInvoice(order);
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

	public static void returnOrder(Scanner scan, int id) {

		ArrayList<Order> orders = orderDAO.findReturnEligible(id);

		System.out.println("\nOnly orders placed in the past 15 days are eligible for return.");

//		orders.forEach(System.out::println);
		for (Order order : orders) {
			
			System.out.println();
			
			order.generateInvoice(order);
		}

		System.out.println("\nSelect the order id you would like to return an item from: ");

		scan.nextLine();
		int orderID = scan.nextInt();

		ArrayList<Shoe> shoes = orderDAO.findShoesOnOrder(orderID);

		shoes.forEach(System.out::println);

		System.out.println("\nPlease select the shoe code you would like to return: ");

		scan.nextLine();
		String code = scan.nextLine();

		List<Shoe> shoe = shoes.parallelStream().filter(s -> s.getShoe_code().equals(code))
				.collect(Collectors.toList());
		
		System.out.println("Shoe to return : " + shoe.get(0));

		shoe.get(0).setStock(shoeDAO.findStock(shoe.get(0)) + 1);
		System.out.println("Stock at: " + shoe.get(0).getStock());
		
		
		shoeDAO.updateStock(shoe.get(0));
		
		orderDAO.removeRelationship(orderID, (int) shoe.get(0).getShoe_id());
		System.out.println("\nShoe returned. No refund though.");

	}

}
