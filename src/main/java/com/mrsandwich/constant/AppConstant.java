package com.mrsandwich.constant;

/**
 * Set the all constant values and use that values in the whole application
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version V1.1
 *
 */
public class AppConstant {

	private AppConstant() {
		
	}
	// Common
	public static final String FAILURE_MESSAGE = "FAILURE";
	public static final String SUCCESS_MESSAGE = "SUCCESS";
	public static final String NO_RECORDS_FOUND = "No Records Found";

	// User
	public static final String USER_NOT_FOUND = "User not found";
	public static final Integer SUCCESS_STATUS_CODE = 200;
	public static final String LOGIN_SCCUESS_MESSAGE = "User Login Successfully";

	// Order
	public static final String ORDER_NOT_FOUND = "Order not found";
	
	//Item
	public static final String ITEM_NOT_FOUND = "Item not found";

	// Place Order
	public static final String PHONEPE_MSG = "Order Placed successfully. Payment throught using Phonepe";
	public static final String PAYTM_MSG = "Order Placed successfully. Payment throught using PayTm";

}
