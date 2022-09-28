package com.cognixia.jump.UI;

import java.util.Scanner;

import com.cognixia.jump.model.User;

public class RegisterUI {
	
	public User registerUser(Scanner scan) {
		
		String input = null;
		
		User user = new User();
		
		System.out.println("Please enter your username: ");
		
		input = scan.next();
		
		
		
		return user;
	}

}
