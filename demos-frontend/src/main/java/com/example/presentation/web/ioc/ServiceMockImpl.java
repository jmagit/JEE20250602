package com.example.presentation.web.ioc;

import javax.enterprise.context.Dependent;

@Mock
@Repository
public class ServiceMockImpl implements Servicio {

	@Override
	public String getNombre() {
		return "Soy la implementacion SIMULADA del servicio";
	}

}
