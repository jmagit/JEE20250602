package com.examples.ws.soap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest {

	@Test
	void testSumaIntInt() {
		Calculadora calculadora = new Calculadora();
		
		double actual = calculadora.suma(2, 3);
		
		assertEquals(5, actual, "La suma de 2 y 3 debe ser 5");
	}

	@ParameterizedTest
	@CsvSource({
		"0, 0, 0",
		"0.1, 0.2, 0.3",
		"1, -0.9, 0.1"
	})
	void testSumas(double a, double b, double expected) {
		Calculadora calculadora = new Calculadora();
		
		double actual = calculadora.suma(a, b);
		
		assertEquals(expected, actual);
	}

//	@Test
//	void testSumaDoubleDouble() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testDivideIntInt() {
		Calculadora calculadora = new Calculadora();
		
		double actual = calculadora.divide(1, 2);
		
		assertEquals(0, actual);
	}

	@Test
	void testDivideDoubleDouble() {
		Calculadora calculadora = new Calculadora();
		
		assertThrows(ArithmeticException.class, () -> calculadora.divide(1.0, 0));
	}

}
