package com.mrsandwich.exception;

/**
 * UserNotfoundException - handle the user not found exception in this custom
 * exception class
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since 07-02-2020
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
