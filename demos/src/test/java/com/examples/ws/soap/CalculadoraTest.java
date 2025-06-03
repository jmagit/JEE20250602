package com.examples.ws.soap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Pruebas de la clase Calculadora")
class CalculadoraTest {
	@Nested
	@DisplayName("Metodo suma")
	class SumaTests {
		@Test
		@DisplayName("Suma de dos enteros")
		void testSumaIntInt() {
			Calculadora calculadora = new Calculadora();

			double actual = calculadora.suma(2, 3);

			assertEquals(5, actual, "La suma de 2 y 3 debe ser 5");
		}

		@ParameterizedTest(name = "{0} + {1} = {2}")
		@CsvSource({ "0, 0, 0", "0.1, 0.2, 0.3", "1, -0.9, 0.1" })
		@DisplayName("Suma de enteros y reales")
		void testSumas(double a, double b, double expected) {
			Calculadora calculadora = new Calculadora();

			double actual = calculadora.suma(a, b);

			assertEquals(expected, actual);
		}
	}

	@Nested
	@DisplayName("Metodo divide")
	class DivideTests {
		@Nested
		class OK {
			@Test
			@DisplayName("División de enteros con resultado enteros")
			void testDivideIntInt() {
				Calculadora calculadora = new Calculadora();

				double actual = calculadora.divide(1, 2);

				assertEquals(0, actual);
			}

			@Test
			@DisplayName("División de reales con resultado real")
			void testDivideDoubleDouble() {
				Calculadora calculadora = new Calculadora();

				double actual = calculadora.divide(1d, 2);

				assertEquals(0.5, actual);
			}
		}

		@Nested
		class KO {
			@Test
			@DisplayName("División de reales por cero")
			void testDivideByZeroDouble() {
				Calculadora calculadora = new Calculadora();

				assertThrows(ArithmeticException.class, () -> calculadora.divide(1.0, 0));
			}
		}
	}
}
