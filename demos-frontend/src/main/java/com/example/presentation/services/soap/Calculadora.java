package com.example.presentation.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


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
	public @javax.jws.WebResult double suma(@WebParam double a, @WebParam double b) { return a + b; }
	@WebMethod
	public double resta(@WebParam double a, @WebParam double b) { return a - b; }
	@WebMethod
	public double multiplica(@WebParam double a, @WebParam double b) { return a * b; }
	@WebMethod
	public double divide(@WebParam double a, @WebParam double b) { return a / b; }
}
