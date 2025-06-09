package com.example.presentation.web.ioc;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Dependent
@Real
@Default
public class ServiceImpl implements Servicio {
	Logger logger = Logger.getLogger(ServiceImpl.class.getName());
	
	@Inject @Version String ver;
	
	public ServiceImpl() {
//		if(ver == null)
//			logger.severe("Sin version");
	}
	
	@PostConstruct
	private void init() {
//			logger.severe("Version: " + ver);
	}
	@PreDestroy
	private void end() {
//		logger.severe("Destruyo");
	}

	@Override
	public String getNombre() {
		return "Soy la implementacion del servicio";
	}

}
