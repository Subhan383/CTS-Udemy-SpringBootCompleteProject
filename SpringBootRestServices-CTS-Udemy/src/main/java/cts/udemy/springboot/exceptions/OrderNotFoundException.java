package cts.udemy.springboot.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 8627309586155490182L;

	public OrderNotFoundException(String message) {
		super(message);

	}

}
