package com.mrsandwich.service;

import org.springframework.stereotype.Service;

import com.mrsandwich.constant.AppConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * This Class provides the service of PayTm payment mode
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version 1.1
 *
 */
@Service("PAYTM")
@Slf4j


	public class PayTm implements PaymentService {

		@Override
		public String payment() {
			log.info("Executing PayTm:Payment succedded using PAYTM");
			return AppConstant.PAYTM_MSG;
		}

	}
