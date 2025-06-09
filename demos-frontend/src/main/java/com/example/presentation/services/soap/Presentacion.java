package com.example.presentation.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Presentacion {
	@WebMethod
	public String saluda(@WebParam String nombre) {
		return "Hola, " + nombre + "!";
	}
	@WebMethod
	public String despide(@WebParam String nombre) {
		return "Adios, " + nombre + "!";
	}
}
