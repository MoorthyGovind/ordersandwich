package com.mrsandwich.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mrsandwich.service.PaymentRegistry;

/**
 * This class contains the configuration details of the
 * servicelocatorFactoryBean
 * 
 * @author Govindasamy.C
 * @since 07-02-2020
 * @version 1.1
 */
@Configuration
public class ServiceLocatorConfiguration {

	@Autowired
	private BeanFactory beanFactory;

	public ServiceLocatorFactoryBean myFactoryLocator() {
		final ServiceLocatorFactoryBean locator = new ServiceLocatorFactoryBean();
		locator.setServiceLocatorInterface(PaymentRegistry.class);
		locator.setBeanFactory(beanFactory);
		return locator;
	}

	@Bean
	public PaymentRegistry myFactory() {
		final ServiceLocatorFactoryBean locator = myFactoryLocator();
		locator.afterPropertiesSet();
		return (PaymentRegistry) locator.getObject();
	}
}
