package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.core.Utils;

class UtilsTest {

	@Test
	void testHelloWorld() {
		Assertions.assertEquals("Hello World from utils !", Utils.helloWorldUtils());
	}

}
