package cts.udemy.springboot.exceptions;

public class UsernameNotFoundException extends Exception {

	

	private static final long serialVersionUID = 7433983009913474405L;

	public UsernameNotFoundException(String message) {
		super(message);

	}

}
