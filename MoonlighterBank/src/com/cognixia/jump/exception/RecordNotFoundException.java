package com.cognixia.jump.exception;

import org.fusesource.jansi.Ansi;

public class RecordNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String exception) {
		System.out.println(Ansi.ansi().fgBrightYellow().a("No records found."));
	}

}