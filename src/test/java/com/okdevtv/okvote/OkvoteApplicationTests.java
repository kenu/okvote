package com.okdevtv.okvote;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.sentry.Sentry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
class OkvoteApplicationTests {

	@Test
	void contextLoads() {
		// sentryTest();
	}

	private void sentryTest() {
		try {
			throw new Exception("This is a test. " + new Date().getTime());
		} catch (Exception e) {
			Sentry.captureException(e);
		}
	}

	@Test
	void testAnother() {
		assertEquals(1, 2 - 1);
	}

}
