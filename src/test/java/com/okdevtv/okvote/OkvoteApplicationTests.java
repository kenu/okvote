package com.okdevtv.okvote;

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

}
