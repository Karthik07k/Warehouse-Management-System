package com.logistics.kk.exception;

public class OrderMethodNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderMethodNotFound() {
		super();
	}
	
	public OrderMethodNotFound(String message) {
		super(message);
	}
}
