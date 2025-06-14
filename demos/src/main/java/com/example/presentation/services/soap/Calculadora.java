package com.example.presentation.services.soap;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.rpc.soap.SOAPFaultException;

@WebService
public class Calculadora {
	public int suma(int a, int b) {
		return a + b;
	}
	@WebMethod
	public double suma(double a, double b) {
		return roundIEEE754(a + b);
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public double divide(double a, double b) {
		if(b == 0) {
			throw new ArithmeticException("/ by zero");
		}
		return roundIEEE754(a / b);
	}
	
	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}

}