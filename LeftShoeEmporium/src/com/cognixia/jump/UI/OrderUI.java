package com.cognixia.jump.UI;

import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.jump.dao.OrderDAO;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.Shoe;

public class OrderUI {

	private static OrderDAO orderDAO = new OrderDAO();

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

		for(Order order : orders) {
			ArrayList<Shoe> shoes = orderDAO.findShoesOnOrder(id);
			
			System.out.println("\n" + order);
			shoes.forEach(System.out::println);
		}

		System.out.println("\nPress enter to exit.....");

		// Twice to clear scanner
		scan.nextLine();
		scan.nextLine();

	}

	public static void submitOrder(Scanner scan) {

	}

	public static void returnOrder(Scanner scan) {

	}

}
