package com.cognixia.jump.exception;

import org.fusesource.jansi.Ansi;

public class BadCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BadCredentialsException(String exception) {
		System.out.println(Ansi.ansi().fgBrightYellow().a("Credentials do not match."));
	}
	
	

}
