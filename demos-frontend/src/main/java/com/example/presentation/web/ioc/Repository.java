package com.example.presentation.web.ioc;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;

@Transactional
@RequestScoped
@Alternative
@Retention(RUNTIME)
@Target(TYPE)
public @interface Repository {

}
