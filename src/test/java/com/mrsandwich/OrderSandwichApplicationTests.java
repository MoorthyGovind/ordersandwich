package com.mrsandwich;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderSandwichApplicationTests {

	@Test
	public void applicationTest() {
		OrderSandwichApplication.main(new String[] {});
		assertTrue(true);
	}

}
