package com.cognixia.jump.Validation;

import java.util.Scanner;

public class Validation {
	
	public static int menuValidation(Scanner scan, String regex) {
		String choice = scan.next();
		while(!choice.matches(regex)) {
			System.out.println("Wrong Input. Try again");
			choice = scan.next();
		}
		
		return Integer.parseInt(choice);
	}
	
	public static String textValidation(Scanner scan, String regex) {
		String choice = scan.nextLine();
		while(!choice.matches(regex)) {
			System.out.println("Wrong Input. Try again");
			choice = scan.nextLine();
		}
		
		return choice;
	}
	

}
