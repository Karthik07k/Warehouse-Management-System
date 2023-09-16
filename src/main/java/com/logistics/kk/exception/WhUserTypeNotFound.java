package com.logistics.kk.exception;

public class WhUserTypeNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WhUserTypeNotFound() {
	}
	
	public WhUserTypeNotFound(String message) {
		super(message);
	}

}
