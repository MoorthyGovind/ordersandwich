package com.mrsandwich.exception;

/**
 * OrderNotfoundException - handle the order not found exception in this custom
 * exception class
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since 07-02-2020
 *
 */
public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
