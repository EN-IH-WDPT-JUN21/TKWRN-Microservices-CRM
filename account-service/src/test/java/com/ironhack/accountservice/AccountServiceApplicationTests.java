package com.ironhack.accountservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceApplicationTests {

	@MockBean
	private AccountServiceApplication application;

	@Test
	void contextLoads() {
	}

}
