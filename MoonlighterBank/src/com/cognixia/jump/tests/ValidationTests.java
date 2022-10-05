package com.cognixia.jump.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ValidationTests {

	@Test
	public void ValidationTest1() {

		String testString = "password123";
		String test = "Passed";

		if (testString.length() < 8) {
			test = "Too short";

		} else if (testString.matches("^[^0-9()]+$")) {
			System.out.println("No digits, password must have at least 1 digit 0-9.");
			test = "No numbers";

		}

		assertEquals("Passed", test);

	}

	@Test
	public void ValidationTest2() {

		String testString = "pass123";
		String test = "Passed";

		if (testString.length() < 8) {
			test = "Too short";

		} else if (testString.matches("^[^0-9()]+$")) {
			System.out.println("No digits, password must have at least 1 digit 0-9.");
			test = "No numbers";

		}

		assertEquals("Too short", test);

	}

	@Test
	public void ValidationTest3() {

		String testString = "password";
		String test = "Passed";

		if (testString.length() < 8) {
			test = "Too short";

		} else if (testString.matches("^[^0-9()]+$")) {
			System.out.println("No digits, password must have at least 1 digit 0-9.");
			test = "No numbers";

		}

		assertEquals("No numbers", test);

	}

	@Test
	public void ValidationTest4() {

		String testString = "pass1word";
		String test = "Passed";

		if (testString.length() < 8) {
			test = "Too short";

		} else if (testString.matches("^[^0-9()]+$")) {
			System.out.println("No digits, password must have at least 1 digit 0-9.");
			test = "No numbers";

		}
		
		assertEquals("Passed", test);
	}

	@Test
	public void ValidationTest5() {

		String testString = "500";
		double balance = 500;
		double total;

		total = balance + Double.parseDouble(testString);
		
		System.out.println(balance);
		
		System.out.println(Double.parseDouble(testString));
		
		System.out.println(total);

		assertEquals(1000, total, 2);

	}

	@Test
	public void stringTest() {

		System.out.println("User: NAME HERE" + "\nusername: USERNAME" + "\npassword: PASSWORD" + "city: CITY"
				+ "\nstreet: STREET" + "\nphoneNumber: NUMBER");
	}

}
