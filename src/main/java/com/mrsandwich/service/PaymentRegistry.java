package com.mrsandwich.service;

import org.springframework.stereotype.Service;

/**
 * This Interface provides the signature of method to get Bean based on the
 * selected Type
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version 1.1
 *
 */
@Service
public interface PaymentRegistry {

	public PaymentService getServiceBean(String paymentType);
}
