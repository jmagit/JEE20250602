package com.example.presentation.services.soap;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;


/*
public class Calculadora {
	
	public double suma( double a,  double b) { return a + b; }
	
	public double resta( double a,  double b) { return a - b; }
	
	public double multiplica( double a,  double b) { return a * b; }
	
	public double divide( double a,  double b) { return a / b; }
}
*/

@WebService()
public class Calculadora {
	@WebMethod
	public double suma(@WebParam double a, @WebParam double b) { return roundIEEE754(a + b); }
//	@WebMethod(operationName = "otraSuma")
//	public double suma(@WebParam int a, @WebParam int b) { return roundIEEE754(a + b); }
	@WebMethod
	public double resta(@WebParam double a, @WebParam double b) { return roundIEEE754(a - b); }
	@WebMethod
	public double multiplica(@WebParam double a, @WebParam double b) { return roundIEEE754(a * b); }
	@WebMethod
	public double divide(@WebParam double a, @WebParam double b) throws SOAPException { 
		if(b == 0) {
			throw new SOAPException("/ by zero");
		}
		return roundIEEE754(a / b); 
	}
	
	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}
}
