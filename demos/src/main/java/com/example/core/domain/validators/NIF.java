package com.example.core.domain.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NIF.Validator.class)
@Documented
public @interface NIF {
	String message() default "{validation.NIF.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	class Validator implements ConstraintValidator<NIF, String> {
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (value == null)
				return true;
			return CadenasValidator.isNIF(value);
		}
	}
}
