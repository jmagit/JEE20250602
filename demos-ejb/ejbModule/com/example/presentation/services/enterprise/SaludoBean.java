package com.example.presentation.services.enterprise;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.example.contracts.distributed.services.SaludoBeanLocal;
import com.example.contracts.distributed.services.SaludoBeanRemote;

/**
 * Session Bean implementation class SaludoBean
 */
@Stateless
@Local(SaludoBeanLocal.class)
public class SaludoBean implements SaludoBeanRemote, SaludoBeanLocal {

	@Override
	public String getSaludoInformal() {
		return "Que passsa";
	}

	@Override
	public String getSaludo() {
		return "Hola";
	}

	@Override
	public String getSaludoFormal() {
		return "Buenos dias";
	}

}
