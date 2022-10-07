package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.core.Utils;

class UtilsTest {

	@Test
	void testHelloWorldFromUtils() {
		assertEquals("Hello World ! (From utils)", Utils.helloWorldFromUtils());
	}

}