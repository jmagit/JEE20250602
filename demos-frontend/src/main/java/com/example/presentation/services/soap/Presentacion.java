package com.example.presentation.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.example.presentation.models.Persona;

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
	@WebMethod
	public Persona getPersona(int id) {
		return new Persona(id, "Pepito", "Grillo", 99);
	}

}
