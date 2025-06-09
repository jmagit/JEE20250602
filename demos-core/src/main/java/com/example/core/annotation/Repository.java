package com.example.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@Target(TYPE)
@Retention(RUNTIME)
@RequestScoped
@Alternative
public @interface Repository {

}
