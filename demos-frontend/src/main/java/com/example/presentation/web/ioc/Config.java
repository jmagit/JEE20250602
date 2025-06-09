package com.example.presentation.web.ioc;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Config {
	@Produces @Version String getVersion() {
		return "v.1.0.0";
	}
}
