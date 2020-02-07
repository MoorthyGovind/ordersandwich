package com.mrsandwich.service;

import org.springframework.stereotype.Service;

import com.mrsandwich.constant.AppConstant;

import lombok.extern.slf4j.Slf4j;
/**
 * This Class provides the service of Phonepe payment mode
 * 
 * @author Chethana
 * @since 05-Feb-2020
 * @version 1.0
 *
 */
@Service("PHONEPE")
@Slf4j
public class PhonePe implements PaymentService{

	@Override
	public String payment() {
		log.info("Executing PayTm:Payment succedded using PAYTM");
		return AppConstant.PHONEPE_MSG;
	}

}
