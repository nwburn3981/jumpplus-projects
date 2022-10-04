package com.cognixia.jump.exception;

public class RecordNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String exception) {
		System.out.println("No records found for that query.");
	}

}