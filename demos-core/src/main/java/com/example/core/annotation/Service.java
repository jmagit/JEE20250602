package com.example.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@Stateless
@Alternative
@RequestScoped
@Target(TYPE)
@Retention(RUNTIME)
public @interface Service {

}
